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
package ch.icclab.cyclops.resource.impl;

import ch.icclab.cyclops.resource.client.InfluxDbClient;
import ch.icclab.cyclops.resource.client.RcServiceClient;
import ch.icclab.cyclops.usecases.tnova.model.*;
import ch.icclab.cyclops.util.Load;
import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.influxdb.dto.BatchPoints;
import org.restlet.data.MediaType;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Manu
 *         Created on 04.12.15.
 */
public class TnovaBillResource extends ServerResource {
    final static Logger logger = LogManager.getLogger(RevenueSharingReportResource.class.getName());

    /**
     * Connects to the RC Service and requests for the CDRs. Fetches the tax and SLA penalties.
     * Constructs the bill response
     *
     * @return String
     */
    @Get
    public String getBillCharges() {
        InfluxDbClient dbClient = new InfluxDbClient();
        String response;
        RcServiceClient client = new RcServiceClient();
        String userId = getQueryValue("userId");
        String fromDate = getQueryValue("from");
        String toDate = getQueryValue("to");
        Date dateFrom = parseDate(fromDate);
        Date dateTo = parseDate(toDate);
        logger.debug("Attempting to get the cdr for the user: "+userId+" from: "+fromDate+" to: "+toDate);
        response = client.getCdrForBill(userId, fromDate, toDate);

        Gson gson = new Gson();

        RevenueSharingReport revenueSharingReport = gson.fromJson(response, RevenueSharingReport.class);
        RevenueSharingEntry[] entries = revenueSharingReport.getRevenues();
        logger.debug("Attempting to add the violations to the gotten CDR report");
        RevenueSharingFullEntry[] charges = addViolationsToReport(entries, dateFrom, dateTo);
        BatchPoints container = dbClient.giveMeEmptyContainer();
        for(int i = 0; i<charges.length; i++){
            container.point(charges[i].toDBPoint());
        }
        logger.debug("Saving entries into the db");
        dbClient.saveContainerToDB(container);

        FullBill result = new FullBill();
        result.setFrom(revenueSharingReport.getFrom());
        result.setTo(revenueSharingReport.getTo());
        result.setUserId(userId);
        result.setCharges(charges);

        return gson.toJson(result);
    }

    private Date parseDate(String date) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formatter.parse(date);
        }catch (Exception e ){
            return null;
        }
    }

    /**
     * This method adds the violations to the response gotten form the CDRs.
     *
     * @param entries
     * @param dateFrom
     * @param dateTo
     * @return
     */
    private RevenueSharingFullEntry[] addViolationsToReport(RevenueSharingEntry[] entries, Date dateFrom, Date dateTo) {
        Gson gson = new Gson();
        ArrayList<String> usedInstances = new ArrayList<String>();
        ArrayList<RevenueSharingFullEntry> result = new ArrayList<RevenueSharingFullEntry>();
        //Used for the easy compute of the discounts, to be updated if we want it more exact.
        entries = computeAvgPrices(entries);
        RevenueSharingFullEntry[] billFullEntry;
        AccountingClient client = new AccountingClient();

        for (int i = 0; i < entries.length; i++) {
            String instanceId = entries[i].getInstanceId();
            logger.debug("Trying to get the violations for the instance: "+instanceId);
            String violationsResponse = client.getServiceViolations(instanceId);
            SLA[] violationArray = gson.fromJson(violationsResponse, SLA[].class);
            for (int o = 0; o < violationArray.length; o++) {
                Date violationTime = getDateFromViolation(violationArray[o].getDatetime());
                if (violationTime.before(dateTo) && violationTime.after(dateFrom)) {
                    if (!usedInstances.contains(instanceId)) {
                        ArrayList<RevenueSharingFullEntry> computedEntries = computeDiscount(violationArray[o].getDefinition(), entries, instanceId);
                        for(RevenueSharingFullEntry entry : computedEntries){
                            result.add(entry);
                        }
                        usedInstances.add(instanceId);
                    }
                }
            }
            if(violationArray.length == 0){
                logger.debug("The instance: "+instanceId+" hasn't got any violation.");
                result.add(new RevenueSharingFullEntry(entries[i], 0));
            }
        }
        billFullEntry = new RevenueSharingFullEntry[result.size()];
        for(int i = 0; i < result.size(); i++){
            billFullEntry[i] = result.get(i);
        }
        return  billFullEntry;
    }

    private Date getDateFromViolation(String date) {
        try {
            String day = date.substring(0,10);
            String hour = date.substring(11,19);
            String dateTime = day.concat(" ").concat(hour);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formatter.parse(dateTime);
        }catch (Exception e ){
            return null;
        }
    }


    /**
     * This method will calculate the average price of the entries and set all of them to the same value.
     *
     * @param entries
     * @return
     */
    private RevenueSharingEntry[] computeAvgPrices(RevenueSharingEntry[] entries) {
        double totalPrice = 0.0;

        for (int i = 0; i < entries.length; i++) {
            totalPrice = totalPrice + entries[i].getPrice();
        }

        totalPrice = totalPrice / entries.length;

        for (int i = 0; i < entries.length; i++) {
            entries[i].setPrice(totalPrice);
        }
        return entries;
    }

    /**
     * This method computes the discount depending on the period that the SLA Definition has.
     *
     * @param definition
     * @param price
     * @return
     */
    private double computePrice(SLADefinition definition, double price) {
        Double expression = Double.parseDouble(definition.getExpression());
        if (definition.getType().equalsIgnoreCase("discount")) {
            if (definition.getUnit().equals("%")) {
                return price * expression / 100;
            } else {
                return expression;
            }
        }
        return 0;
    }

    /**
     * This method returns an ArrayList of RevenueFullEntries created from the RevenueSharingEntry array "entries" for a given instanceId
     *
     * @param definition
     * @param entries
     * @param instanceId
     * @return
     */
    private ArrayList<RevenueSharingFullEntry> computeDiscount(SLADefinition definition, RevenueSharingEntry[] entries, String instanceId) {
        //TODO: Revisar si el caso de uso de la resta entera se cumple bien.
        ArrayList<RevenueSharingFullEntry> revenueSharingFullEntries = new ArrayList<RevenueSharingFullEntry>();
        String validityUnit = definition.getValidity().substring(2);
        int validityPeriod = Integer.parseInt(definition.getValidity().substring(1, 2));
        double discountValue = 0;
        logger.debug("Compute discount for period: "+definition.getValidity());
        if (validityUnit.equalsIgnoreCase("D")) {
            logger.debug("Computing discount for DAYS");
            int modifiedInstances = 0;
            for(int i = 0; i < entries.length; i++){
                if(entries[i].getInstanceId().equals(instanceId)) {
                    if (modifiedInstances < validityPeriod) {
                        discountValue = computePrice(definition, entries[i].getPrice());
                        modifiedInstances++;
                    }
                    logger.debug("Adding discount : "+discountValue+" to the entries.");
                    revenueSharingFullEntries.add(new RevenueSharingFullEntry(entries[i], discountValue));
                }
            }
        } else if (validityUnit.equalsIgnoreCase("W")) {
            logger.debug("Computing discount for WEEKS");
            int modifiedInstances = 0;
            for(int i = 0; i < entries.length; i++) {
                if (entries[i].getInstanceId().equals(instanceId)) {
                    if (modifiedInstances < validityPeriod * 7) {
                        discountValue = computePrice(definition, entries[i].getPrice());
                        modifiedInstances++;
                    }
                    logger.debug("Adding discount : "+discountValue+" to the entries.");
                    revenueSharingFullEntries.add(new RevenueSharingFullEntry(entries[i], discountValue));
                }
            }
        } else if (validityUnit.equalsIgnoreCase("H")) {
            logger.debug("Computing discount for HOURS");
            int modifiedInstances = 0;
            for(int i = 0; i < entries.length; i++) {
                if (entries[i].getInstanceId().equals(instanceId)) {
                    if (modifiedInstances < Math.ceil(validityPeriod / 24)) {
                        discountValue = computePrice(definition, entries[i].getPrice());
                        modifiedInstances++;
                    }
                    logger.debug("Adding discount : "+discountValue+" to the entries.");
                    revenueSharingFullEntries.add(new RevenueSharingFullEntry(entries[i], discountValue));
                }
            }
        }
        return revenueSharingFullEntries;
    }

}
