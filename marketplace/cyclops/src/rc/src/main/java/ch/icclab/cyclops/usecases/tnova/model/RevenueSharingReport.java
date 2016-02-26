package ch.icclab.cyclops.usecases.tnova.model;

import java.util.HashMap;

/**
 * @author Manu
 *         Created on 04.12.15.
 */
public class RevenueSharingReport {
    private HashMap<String, Double> revenues;
    private HashMap<String, String> time;
    private String VFProvider;
    private String SProvider;

    public void setTime(String from, String to) {
        this.time = new HashMap<String, String>();
        this.time.put("from", from);
        this.time.put("to", to);
    }

    public void setPrice(HashMap<String, Double> values) {
        this.revenues = values;
    }

    public void setVFProvider(String VFProvider) {
        this.VFProvider = VFProvider;
    }

    public void setSProvider(String SProvider) {
        this.SProvider = SProvider;
    }
}
