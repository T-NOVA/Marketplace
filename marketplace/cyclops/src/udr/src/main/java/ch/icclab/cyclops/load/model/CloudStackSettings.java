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

package ch.icclab.cyclops.load.model;

/* Author: Martin Skoviera
 * Created on: 16-Nov-15
 * Description: Settings for CloudStack
 */
public class CloudStackSettings {
    private String CloudStackURL;
    private String CloudStackAPIKey;
    private String CloudStackSecretKey;
    private Integer CloudStackPageSize;
    private String CloudStackDBLogsName;
    private String CloudStackEventMeasurement;
    private String CloudStackMeterListSelection;
    private String CloudStackImportFrom;
    private static final Integer default_page_size = 500;
    private ThreeStates ExtendedCloudStack;

    /**
     * Simple constructor that will save provided fields
     */
    public CloudStackSettings(String cloudStackURL, String cloudStackAPIKey, String cloudStackSecretKey,
                              String cloudStackPageSize, String cloudStackDBLogsName, String cloudStackEventMeasurement,
                              String cloudStackMeterListSelection) {

        CloudStackURL = cloudStackURL;
        CloudStackAPIKey = cloudStackAPIKey;
        CloudStackSecretKey = cloudStackSecretKey;
        CloudStackDBLogsName = cloudStackDBLogsName;
        CloudStackEventMeasurement = cloudStackEventMeasurement;
        CloudStackMeterListSelection = cloudStackMeterListSelection;

        // now determine proper page size
        try {
            Integer pageSize = Integer.parseInt(cloudStackPageSize);

            // should we save it?
            if (pageSize > 0 && pageSize < default_page_size) {
                CloudStackPageSize = pageSize;
            } else {
                CloudStackPageSize = default_page_size;
            }
        } catch (Exception e) {
            CloudStackPageSize = default_page_size;
        }

        // now question is whether we are using vanilla or extended cloudstack
        ExtendedCloudStack = new ThreeStates();
    }

    //==== we only need getters
    public String getCloudStackURL() {
        return CloudStackURL;
    }
    public String getCloudStackAPIKey() {
        return CloudStackAPIKey;
    }
    public String getCloudStackSecretKey() {
        return CloudStackSecretKey;
    }
    public Integer getCloudStackPageSize() {
        return CloudStackPageSize;
    }
    public String getCloudStackDBLogsName() {
        return CloudStackDBLogsName;
    }
    public String getCloudStackEventMeasurement() {
        return CloudStackEventMeasurement;
    }
    public String getCloudStackMeterListSelection() {
        return CloudStackMeterListSelection;
    }

    public String getCloudStackImportFrom() {
        return CloudStackImportFrom;
    }
    public void setCloudStackImportFrom(String cloudStackImportFrom) {
        CloudStackImportFrom = cloudStackImportFrom;
    }

    //==== Custom class for three states
    private class ThreeStates {
        private Boolean unset = true;
        private Boolean state = false;

        public Boolean isStateUnset() {
            return unset;
        }

        public void setState(Boolean s) {
            state = s;
            unset = false;
        }

        public Boolean getState() {
            return state;
        }
    }
    //==== implementation of extended cloudstack setter
    public Boolean isExtendedCloudStackFlagUnset() {
        return ExtendedCloudStack.isStateUnset();
    }
    public Boolean extendedCloudStack() {
        return ExtendedCloudStack.getState();
    }
    public void setExtendedCloudStack() {
        ExtendedCloudStack.setState(true);
    }
    public void setVanillaCloudStack() {
        ExtendedCloudStack.setState(false);
    }
}
