/**
* Copyright 2015 Atos
* Contact: Atos <javier.melian@atos.net>
*
*    Licensed under the Apache License, Version 2.0 (the "License");
*    you may not use this file except in compliance with the License.
*    You may obtain a copy of the License at
*
*        http://www.apache.org/licenses/LICENSE-2.0
*
*    Unless required by applicable law or agreed to in writing, software
*    distributed under the License is distributed on an "AS IS" BASIS,
*    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*    See the License for the specific language governing permissions and
*    limitations under the License.
*/


package eu.atos.sla.monitoring.simple;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import antlr.Utils;
//import eu.atos.sla.evaluation.guarantee.PoliciedServiceLevelEvaluator;
import eu.atos.sla.monitoring.IMetricsRetriever;
import eu.atos.sla.monitoring.IMonitoringMetric;

/**
 * metrics retriver from an external monitor
 * 
 * @author jmelian
 *
 */
public class TNovaMetricsRetriever implements IMetricsRetriever {

	public TNovaMetricsRetriever() {
	}
	
	private static Logger logger = LoggerFactory.getLogger(TNovaMetricsRetriever.class);
        private static final String NETWORK_SERVICE = "ns";
        private static final String VNF = "vnf";
        private static final String ORCHESTRATOR_URL = "http://10.10.1.61:4000/instances/";

	
        private static Long DateToUnixtime(Date time) {
            //DateFormat dfm = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");

            long unixtime = 0;
            unixtime = time.getTime();  
            unixtime=unixtime/1000;
            return unixtime;
        }

	@Override
	public List<IMonitoringMetric> getMetrics(String serviceId, String serviceScope,
			final String variable, Date begin, final Date end, int maxResults) {
                String instanceId; String instanceType;

		if (begin == null) {
			
			begin = new Date(end.getTime() - 1000);
		}

		//Change the format of the dates to unix timestamp
                //DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
                Long dateBegin = DateToUnixtime(begin)-120; //move the monitoring requests 120secs back
                Long dateEnd = DateToUnixtime(end)-120;
                
                //extract the instanceId and the instanceType from the variable serviceId
                if (serviceId.contains(VNF)) {
                    instanceId = serviceId.replaceFirst(VNF, "");
                    instanceType = VNF;
                } 
                else { //we assume it's a network service
                    instanceId = serviceId.replaceFirst(NETWORK_SERVICE, "");
                    instanceType = NETWORK_SERVICE;
                }


		JSONParser jParser = new JSONParser();
		String url = ORCHESTRATOR_URL + instanceId + "/monitoring-data/?instance_type=" + instanceType + "&metric=" + variable + "&start=" + dateBegin + "&end=" + dateEnd;
		//String url = "http://apis.t-nova.eu/orchestrator/instances/10/monitoring-data/?instance_type=ns&metric=" + variable;
		/*//Header for the API call 
		String token = Utils.getToken(source);

		List<NameValuePair> headerParams = new ArrayList<NameValuePair>(1);
		headerParams.add(new BasicNameValuePair("Authorization", "Token " + token));
		*/
		
		logger.debug("TNOVA: serviceId: {}, metric: {}, begin: {}:{}, end: {}:{}",  serviceId, variable, dateBegin, begin, dateEnd, end);
		
                JSONArray results = jParser.getJSONArrayFromUrl(url);

		logger.debug("TNOVA: Metrics- {}",  results);
		logger.debug("TNOVA: URL- {}",  url);

		List<IMonitoringMetric> values = new ArrayList<IMonitoringMetric>();
		try 
		{
			//Obtention of the array of json objects (each one containing a metric value) from the monitor
			//JSONArray results = json.getJSONArray("results");
			for(int i = 0, size = results.length(); i < size; i++)
			{
				JSONObject jsonMetric = results.getJSONObject(i);
				values.add(getMetric(jsonMetric));
				//IMonitoringMetric test = getMetric(jsonMetric);
				//logger.debug("TNOVA: monitoringMetric {} = metric: {}, value: {}, date: {}", i, test.getMetricKey(), test.getMetricValue(), test.getDate());
			}
		}
		catch (JSONException e)
		{
			logger.debug("TNOVA: ERROR- {}", e.toString());
		}
		return values;
	}
	
	/**
	 * Converts a json object retrieved from the monitoring system to a IMonitoringMetric type: name, value, date.
	 * 
	 * @author jmelian
	 *
	 */
	public IMonitoringMetric getMetric(JSONObject metric) {
		Date date = null; String name = null; Double value = null;
		try {
			name = metric.getString("metricname");
			value = Double.parseDouble(metric.getString("value"));
			//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			//timestamp = metric.getDouble("date");
                        //date = new java.util.Date((long)timeStamp*1000);

                        //Convert the dates from unix time to regular dates
			date = new java.util.Date((Long)metric.getLong("date")*1000);
		} catch (JSONException e) {
			logger.debug("TNOVA: ERROR- {}", e.toString());
		}
		
		return new MonitoringMetric(name, value, date);
	}
}

