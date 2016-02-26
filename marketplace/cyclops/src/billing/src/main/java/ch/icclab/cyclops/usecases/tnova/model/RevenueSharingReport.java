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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Manu
 *         Created on 09.12.15.
 */
public class RevenueSharingReport {
    private String provider;
    private String from;
    private String to;
    private RevenueSharingEntry[] revenues;

    public String getProvider() {
        return provider;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public RevenueSharingEntry[] getRevenues() {
        return revenues;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setRevenues(List<List<Object>> points, List<String> columns) {
        int providerIndex = columns.indexOf("provider");
        int instanceIdIndex = columns.indexOf("instanceId");
        int priceIndex = columns.indexOf("price");
        int priceUnitIndex = columns.indexOf("priceUnit");
        int timeIndex = columns.indexOf("time");
        int userIdIndex = columns.indexOf("userId");

        ArrayList<RevenueSharingEntry> revenues = new ArrayList<RevenueSharingEntry>();
        for (List<Object> point : points) {
            RevenueSharingEntry revenue = new RevenueSharingEntry();
            revenue.setProvider((String) point.get(providerIndex));
            revenue.setInstanceId((String) point.get(instanceIdIndex));
            revenue.setPrice((Double) point.get(priceIndex));
            revenue.setPriceUnit((String) point.get(priceUnitIndex));
            revenue.setTime(String.valueOf(point.get(timeIndex)));
            revenue.setUserId(String.valueOf(point.get(userIdIndex)));

            revenues.add(revenue);
        }

        this.revenues = new RevenueSharingEntry[(revenues.size())];
        this.revenues = revenues.toArray(this.revenues);
    }
}
