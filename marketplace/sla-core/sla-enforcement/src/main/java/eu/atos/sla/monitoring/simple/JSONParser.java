package eu.atos.sla.monitoring.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JSONParser {

static InputStream is = null;
static JSONObject jObj = null;
static JSONArray jaObj = null;
static String json = "";
private static Logger logger = LoggerFactory.getLogger(JSONParser.class);


// constructor
public JSONParser() {

}

public JSONObject getJSONFromUrl(String url) {

    // Making HTTP request
    try {
        // defaultHttpClient
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);

        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        is = httpEntity.getContent();

    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    } catch (ClientProtocolException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

    try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                is, "iso-8859-1"), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
            System.out.println(line);
        }
        is.close();
        json = sb.toString();

    } catch (Exception e) {
        logger.debug("TNOVA: JSONParser Buffer error. Error converting result- {}", e.toString());
    }

    // try parse the string to a JSON object
    try {
        jObj = new JSONObject(json);
    } catch (JSONException e) {
        logger.debug("TNOVA: JSONParser. Error parsing data- {}", e.toString());
        System.out.println("error on parse data in jsonparser.java");
    }

    // return JSON String
    return jObj;

}


public JSONArray getJSONArrayFromUrl(String url) {

    // Making HTTP request
    try {
        // defaultHttpClient
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);

        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        is = httpEntity.getContent();

    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    } catch (ClientProtocolException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

    try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                is, "iso-8859-1"), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
            System.out.println(line);
        }
        is.close();
        json = sb.toString();
        logger.debug("RRRRRRRRRRRRRRRRR: {}", json);

    } catch (Exception e) {
        logger.debug("TNOVA: JSONParser Buffer error. Error converting result- {}", e.toString());
    }

    // try parse the string to a JSON array
    try {
        jaObj = new JSONArray(json);
    } catch (JSONException e) {
        logger.debug("TNOVA: JSONParser. Error parsing data- {}", e.toString());
        System.out.println("error on parse data in jsonparser.java");
    }

    // return JSON String
    return jaObj;

}
}
