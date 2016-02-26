package ch.icclab.cyclops.usecases.tnova.resource;

import ch.icclab.cyclops.database.InfluxDBClient;
import ch.icclab.cyclops.load.Loader;
import ch.icclab.cyclops.model.ChargeResponse;
import ch.icclab.cyclops.model.TSDBData;
import ch.icclab.cyclops.util.APICallCounter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.util.HashMap;

/**
 * @author Manu
 *         Created on 03.12.15.
 */
public class RevenueSharingResource extends ServerResource {
    // who am I?
    private String endpoint = "/revenue";

    // used as counter
    private APICallCounter counter = APICallCounter.getInstance();

    /**
     * Queries the database to get the charge data records for a given time period
     * <p/>
     * Pseudo Code
     * 1. Get the userid , from and to details from the API query parameters
     * 2. Query the database to get the cdr
     * 3. Construct the response and return the json string
     *
     * @return Representation
     */
    @Get
    public Representation getChargeRecords() {

        counter.increment(endpoint);

        InfluxDBClient dbClient = new InfluxDBClient();
        HashMap cdrMap = new HashMap();
        TSDBData tsdbData;
        Representation response;

        String userId = getQueryValue("userId");
        String provider = getQueryValue("provider");
        String fromDate = getQueryValue("from");//normalizeDateAndTime(getQueryValue("from"));
        String toDate = getQueryValue("to");//normalizeDateAndTime(getQueryValue("to"));

        if (userId == null && provider != null) {
            tsdbData = dbClient.getTnovaData("SELECT * FROM tnova_revenue_sharing WHERE provider='" + provider + "' AND time > '" + fromDate + "' AND time < '" + toDate + "'");
        } else {
            if (provider != null) {
                //esto te devuelve la factura
                tsdbData = dbClient.getTnovaData("SELECT * FROM tnova_revenue_sharing WHERE userId='" + userId + "' AND provider='" + provider + "' AND time > '" + fromDate + "' AND time < '" + toDate + "'");
            } else
                tsdbData = dbClient.getTnovaData("SELECT * FROM tnova_revenue_sharing WHERE userId='" + userId + "' AND time > '" + fromDate + "' AND time < '" + toDate + "'");
        }
        cdrMap.put("columns", tsdbData.getColumns());
        cdrMap.put("points", tsdbData.getPoints());
        response = constructResponse(cdrMap, provider, fromDate, toDate);
        return response;
    }

    /**
     * Construct the JSON response consisting of the meter and the usage values
     * <p/>
     * Pseudo Code
     * 1. Create the HasMap consisting of time range
     * 2. Create the response POJO
     * 3. Convert the POJO to JSON
     * 4. Return the JSON string
     *
     * @param rateArr  An arraylist consisting of metername and corresponding usage
     * @param fromDate DateTime from usage data needs to be calculated
     * @param toDate   DateTime upto which the usage data needs to be calculated
     * @return responseJson The response object in the JSON format
     */
    public Representation constructResponse(HashMap rateArr, String userid, String fromDate, String toDate) {

        String jsonStr;
        JsonRepresentation responseJson = null;

        ChargeResponse responseObj = new ChargeResponse();
        HashMap time = new HashMap();
        ObjectMapper mapper = new ObjectMapper();

        time.put("from", fromDate);
        time.put("to", toDate);

        //Build the response POJO
        responseObj.setUserid(userid);
        responseObj.setTime(time);
        responseObj.setCharge(rateArr);

        //Convert the POJO to a JSON string
        try {
            jsonStr = mapper.writeValueAsString(responseObj);
            responseJson = new JsonRepresentation(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return responseJson;
    }

    /**
     * Remove ' character and replace T with a space
     *
     * @param time
     * @return
     */
    private String normalizeDateAndTime(String time) {
        String first = time.replace("'", "");
        return first.replace("T", " ");
    }

    public String getProviderId(String instanceId) {
        InfluxDBClient dbClient = new InfluxDBClient();
        TSDBData[] tsdbData = null;

        String parameterQuery = "SELECT providerId, id FROM events where instanceId='" + instanceId + "' order by time desc limit 1";
        tsdbData = dbClient.query(parameterQuery, Loader.getSettings().getInfluxDBSettings().getEventsDbName());

        int providerIdIndex = -1;
        for (int i = 0; i < tsdbData[0].getColumns().size() && providerIdIndex < 0; i++) {
            if (tsdbData[0].getColumns().get(i).equals("providerId"))
                providerIdIndex = i;
        }
        String provider = (String) tsdbData[0].getPoints().get(0).get(providerIdIndex);
        return provider;
    }
}
