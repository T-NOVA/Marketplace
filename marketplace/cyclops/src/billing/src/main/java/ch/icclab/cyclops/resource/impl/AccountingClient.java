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

import ch.icclab.cyclops.util.Load;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

/**
 * @author Manu
 *         Created on 20.12.16.
 */
public class AccountingClient extends ClientResource {
    private String url = Load.configuration.get("AccountingUrl");
    final static Logger logger = LogManager.getLogger(AccountingClient.class.getName());


    /**
     * This method gets the violations for a given instanceId of a vnf
     *
     * @param instanceId
     * @return
     */
    public String getVnfViolations(String instanceId) {
        JSONArray resultArray = null;

        ClientResource resource = new ClientResource(url + "/sla/vnf-violation/?instanceId=" + instanceId);
        logger.debug("Call to accounting: " + url + "/sla/vnf-violation/?instanceId=" + instanceId);
        try {
            resource.get(MediaType.APPLICATION_JSON);
            Representation output = resource.getResponseEntity();
            String outputText = output.getText().toString();
            logger.debug("Response from accounting: " + outputText);
            resultArray = new JSONArray(outputText);
        } catch (Exception e) {
            logger.error("Error getting the response from accounting: " + e.getMessage());
        }

        return resultArray.toString();
    }

    /**
     * This method gets the violations for a given instanceId of a service
     *
     * @param instanceId
     * @return
     */
    public String getServiceViolations(String instanceId) {
        JSONArray resultArray = null;

        ClientResource resource = new ClientResource(url + "/sla/service-violation/?instanceId=" + instanceId);
        logger.debug("Call to accounting: " + url + "/sla/service-violation/?instanceId=" + instanceId);
        try {
            resource.get(MediaType.APPLICATION_JSON);
            Representation output = resource.getResponseEntity();
            String outputText = output.getText().toString();
            logger.debug("Response from accounting: " + outputText);
            resultArray = new JSONArray(outputText);
        } catch (Exception e) {
            logger.error("Error getting the response from accounting: " + e.getMessage());
        }

        return resultArray.toString();
    }
}
