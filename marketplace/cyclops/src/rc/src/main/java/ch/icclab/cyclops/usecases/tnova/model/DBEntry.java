package ch.icclab.cyclops.usecases.tnova.model;

import ch.icclab.cyclops.util.DateTimeUtil;
import org.influxdb.dto.Point;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Manu
 *         Created on 04.12.15.
 */
public abstract class DBEntry {

    private String name;
    private String time;

    /**
     * Create an InfluxDB Point that can be saved into InfluxDB database
     * @return
     */
    public Point toDBPoint() {

        Map tags = getTags();
        removeNullValues(tags);

        Map fields = getFields();
        removeNullValues(fields);

        // now return constructed point
        return Point.measurement(name)
                .time(DateTimeUtil.getMillisForTime(time), TimeUnit.MILLISECONDS)
                .tag(tags)
                .fields(fields)
                .build();
    }

    /**
     * This method returns default tags
     * @return
     */
    protected abstract Map<String, String> getTags();

    /**
     * This method returns default fields
     * @return
     */
    protected abstract Map<String, Object> getFields();

    /**
     * Make sure we are not having any null values
     * @param map original container that has to be changed
     */
    private void removeNullValues(Map<Object, Object> map) {
        map.values().removeAll(Collections.singleton(null));
    }
}
