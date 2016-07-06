package eu.atos.sla.monitoring.simple;

import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.text.DateFormat;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.atos.sla.monitoring.IMetricsRetriever;
import eu.atos.sla.monitoring.IMonitoringMetric;

/**
 * Dummy retriver that returns metric values in [0,1)
 * 
 * @author rsosa
 *
 */
public class DummyMetricsRetriever implements IMetricsRetriever {

	private final Random rnd = new Random();
	
	public DummyMetricsRetriever() {
	}
	
	private static Logger logger = LoggerFactory.getLogger(TNovaMetricsRetriever.class);

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

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
                //DateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                DateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                fullDateFormat.setTimeZone(TimeZone.getTimeZone("CET"));
		if (begin == null) {
			
			begin = new Date(end.getTime() - 1000);
		}
		logger.debug("DUMMY: DATE: " + begin.getTime());

                long interval = end.getTime() - begin.getTime();
                interval = (long)(interval/3);

                Date new_begin = new Date(begin.getTime() + interval);
                Date new_end = new Date(end.getTime() - interval);
		List<IMonitoringMetric> result = new ArrayList<IMonitoringMetric>();
                try {
                    File file =new File("metricsDB.csv");
                
                    //if file doesnt exists, then create it
                    if(!file.exists()){
                        file.createNewFile();
                        FileWriter fileWriter = new FileWriter(file.getName(), true);
                        BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
                        bufferWriter.write("AgreementId,Metric,Value,Date");
                        bufferWriter.newLine();
                        bufferWriter.close();
                    }
                    //System.exit(0);
                    FileWriter fileWriter = new FileWriter(file.getName(), true);
                    BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
		    logger.debug("DUMMY: begin: " + new_begin + ", end: " + new_end);
		    for (int i = 0; i < 2; i++) {
                        IMonitoringMetric randomMetric = getRandomMetric(variable, i == 0? new_begin : new_end);
                        Date date = randomMetric.getDate(); 
                        bufferWriter.write("" + serviceId + "," + randomMetric.getMetricKey() + "," + randomMetric.getMetricValue() + "," + fullDateFormat.format(date));
                        //bufferWriter.write("" + serviceId + "," + randomMetric.getMetricKey() + "," + randomMetric.getMetricValue() + "," + date);
                        bufferWriter.newLine();
	    	        result.add(randomMetric);
	    	    }
                    bufferWriter.close();
                } catch(IOException e){
                    e.printStackTrace();
                }
		return result;
	}
	
	public IMonitoringMetric getRandomMetric(String metricName, Date date) {
		
		return new MonitoringMetric(metricName, rnd.nextDouble(), date);
	}

}
