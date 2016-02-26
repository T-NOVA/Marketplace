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

import org.joda.time.DateTime;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Manu
 *         Created on 04.12.15.
 */
public class RevenueSharingEntry {

    private String name = "tnova_revenue_sharing";
    private String instanceId;
    private double price;
    private String priceUnit;
    private String time;
    private String userId;
    private String provider;

    public RevenueSharingEntry() {
    }


    private double computeCost(TnovaBillingModel billingModel, TnovaCDREntry cdr) {
        if (billingModel.getBillingModel().equals("%")) {
            return (billingModel.getPeriodCost() / 100) * cdr.getPrice();
        } else {
            return billingModel.getPeriodCost() * cdr.getPrice();
        }
    }


    /**
     * Will compute number of milliseconds from epoch to startDate
     *
     * @param time as string
     * @return milliseconds since epoch
     */
    public static long getMillisForTime(String time) {
        // first we have to get rid of 'T', as we need just T
        String isoFormat = time.replace("'", "");

        // then we have to create proper formatter
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ")
                .withLocale(Locale.ROOT)
                .withChronology(ISOChronology.getInstanceUTC());

        // and now parse it
        DateTime dt = formatter.parseDateTime(isoFormat);

        return dt.getMillis();
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
//        map.put("VNFProvider", VNFProvider);
//        map.put("SProvider", SProvider);
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

        return map;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public String getTime() {
        return time;
    }

    private void removeNullValues(Map<Object, Object> map) {
        map.values().removeAll(Collections.singleton(null));
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

}
