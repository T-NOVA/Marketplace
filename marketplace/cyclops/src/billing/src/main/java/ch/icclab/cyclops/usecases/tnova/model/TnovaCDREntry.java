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

import ch.icclab.cyclops.model.UDREntry;
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
 * Author: Martin Skoviera
 * Created on: 03-Nov-15
 * Description: Holds CDR Entry object after calculation of all required fields
 */
public class TnovaCDREntry{
    private String name = "tnova_cdr";
    private String time;
    private String productId;
    private String resource; // basically instanceId
    private String userId;   // basically clientId
    private double price;
    private String priceUnit;
    private String period;
    private double periodCost;
    private double setupCost;
    private String usage;
    private String providerId;


    /**
     * Construct and populate TnovaCDREntry object
     * @param udr object
     * @param model for billing
     * @param clientId as a string
     */
    public TnovaCDREntry(UDREntry udr, TnovaBillingModel model, String clientId) {
        // basic header
        this.time = udr.getTime();
        this.resource = udr.getInstanceId();
        this.periodCost = model.getPeriodCost();
        this.providerId = udr.getProviderId();
        this.userId = clientId;
        this.priceUnit = model.getPriceUnit();
        // compute price and save usage
        this.usage = udr.getUsage();
        this.price = model.computeCost(Double.parseDouble(this.usage));
    }

    /**
     * Will compute number of milliseconds from epoch to startDate
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
     * @return
     */
    protected Map<String, String> getTags() {
        Map<String, String> map = new HashMap<String, String>();

        // now add default tags
        map.put("productId", productId);
        map.put("resource", resource);
        map.put("userId", userId);
        map.put("providerId", providerId);

        return map;
    }

    /**
     * This method returns default fields
     * @return
     */
    protected Map<String, Object> getFields() {
        Map<String, Object> map = new HashMap<String, Object>();

        // now add default fields
        map.put("price", price);
        map.put("priceUnit", priceUnit);
        map.put("period", period);
        map.put("periodCost", periodCost);
        map.put("setupCost", setupCost);
        map.put("usage", usage);

        return map;
    }

    /**
     * Make sure we are not having any null values
     * @param map original container that has to be changed
     */
    private void removeNullValues(Map<Object, Object> map) {
        map.values().removeAll(Collections.singleton(null));
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getProductId() {
        return productId;
    }

    public String getResource() {
        return resource;
    }

    public String getUserId() {
        return userId;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public String getPeriod() {
        return period;
    }

    public double getPeriodCost() {
        return periodCost;
    }

    public double getSetupCost() {
        return setupCost;
    }

    public String getUsage() {
        return usage;
    }

    public String getProviderId() {
        return providerId;
    }
}
