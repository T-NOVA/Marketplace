/*
 * Copyright (c) 2015. Zuercher Hochschule fuer Angewandte Wissenschaften
 *  All Rights Reserved.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License"); you may
 *     not use this file except in compliance with the License. You may obtain
 *     a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *     WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *     License for the specific language governing permissions and limitations
 *     under the License.
 */
package ch.icclab.cyclops.usecases.tnova.model;

import ch.icclab.cyclops.util.DateTimeUtil;
import ch.icclab.cyclops.util.Load;
import org.influxdb.dto.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Manu on 20/12/15.
 */
public class RevenueSharingFullEntry {
    private String time;
    private String instanceId;
    private String provider;
    private String usage;
    private Double price;
    private String priceUnit;
    private Double discountValue;
    private Double finalPrice;
    private Integer totalViolations;
    private String userId;

    public RevenueSharingFullEntry(RevenueSharingEntry revenueSharingEntry, Double discountValue, Integer totalViolations) {
        this.time = revenueSharingEntry.getTime();
        this.userId = revenueSharingEntry.getUserId();
        this.instanceId = revenueSharingEntry.getInstanceId();
        this.provider = revenueSharingEntry.getProvider();
        this.price = revenueSharingEntry.getPrice();
        this.priceUnit = revenueSharingEntry.getPriceUnit();
        this.discountValue = discountValue;
        this.finalPrice = this.price - this.discountValue;
        this.totalViolations = totalViolations;
    }

    public RevenueSharingFullEntry(RevenueSharingEntry revenueSharingEntry, double discountValue) {
        this.time = revenueSharingEntry.getTime();
        this.instanceId = revenueSharingEntry.getInstanceId();
        this.provider = revenueSharingEntry.getProvider();
        this.price = revenueSharingEntry.getPrice();
        this.priceUnit = revenueSharingEntry.getPriceUnit();
        this.discountValue = discountValue;
        this.totalViolations = 0;
        this.finalPrice = this.price - this.discountValue;
    }

    /**
     * This method returns default tags
     *
     * @return
     */
    protected Map<String, String> getTags() {
        Map<String, String> map = new HashMap<String, String>();

        // now add default tags
        map.put("instanceId", instanceId);
        map.put("provider", provider);
        map.put("userId", userId);

        return map;
    }

    /**
     * This method returns default fields
     *
     * @return
     */
    protected Map<String, Object> getFields() {
        Map<String, Object> map = new HashMap<String, Object>();

        // now add default fields
        map.put("price", price);
        map.put("priceUnit", priceUnit);
        map.put("usage", usage);
        map.put("discountValue", discountValue);
        map.put("totalViolations", totalViolations);
        map.put("finalPrice", finalPrice);

        return map;
    }

    /**
     * Create an InfluxDB Point that can be saved into InfluxDB database
     *
     * @return
     */
    public Point toDBPoint() {

        Map tags = getTags();
        removeNullValues(tags);

        Map fields = getFields();
        removeNullValues(fields);

        // now return constructed point
        return Point.measurement(Load.configuration.get("RevenueSharingTableName"))
                .time(DateTimeUtil.getMillisForTime(time), TimeUnit.MILLISECONDS)
                .tag(tags)
                .fields(fields)
                .build();
    }

    private void removeNullValues(Map<Object, Object> map) {
        map.values().removeAll(Collections.singleton(null));
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Double getSlaPrice() {
        return discountValue;
    }

    public void setSlaPrice(Double discountValue) {
        this.discountValue = discountValue;
    }

    public Integer getTotalViolations() {
        return totalViolations;
    }

    public void setTotalViolations(Integer totalViolations) {
        this.totalViolations = totalViolations;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public void setFields(ArrayList<Object> value, ArrayList<String> columns) {
        this.setTime((String) value.get(columns.indexOf("time")));
        this.setInstanceId((String) value.get(columns.indexOf("instanceId")));
        this.setProvider((String) value.get(columns.indexOf("provider")));
        this.setUsage((String) value.get(columns.indexOf("usage")));
        this.setPrice(getDoubleValue(value.get(columns.indexOf("price"))));
        this.setPriceUnit((String) value.get(columns.indexOf("priceUnit")));
    }

    //This method is going to add to the object the values coming from the SLA response. regarding to violations.
    public void setPrices(ArrayList<Object> values, ArrayList<String> columns) {
        this.setFinalPrice(getDoubleValue(values.get(columns.indexOf("finalPrice"))));
        this.setTotalViolations((Integer) values.get(columns.indexOf("totalViolations")));
        this.setSlaPrice(getDoubleValue(values.get(columns.indexOf("slaPrice"))));

    }

    private Double getDoubleValue(Object number) {
        try {
            return (Double) number;
        } catch (Exception e) {
            return Double.parseDouble(number.toString());
        }
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }
}
