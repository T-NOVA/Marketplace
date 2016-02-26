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
package ch.icclab.cyclops.services.iaas.cloudstack.model.safeswisscloud;

import ch.icclab.cyclops.services.iaas.cloudstack.model.UsageData;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Martin Skoviera
 * Created on: 11-Nov-15
 * Description: POJO object for VMs CPU compute usage records (type -5)
 */
public class VMCpuUsageData extends UsageData {

    // attributes for CPU usage for VMs
    private String cpuspeed;
    private String vcpu;
    private String offeringid;
    private String name;

    @Override
    protected Map<String, String> getObjectTags() {
        Map<String, String> map = new HashMap<String, String>();

        map.put("offeringid", offeringid);
        map.put("name", name);

        return map;
    }

    @Override
    protected Map<String, Object> getObjectFields() {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("cpuspeed", cpuspeed);
        map.put("vcpu", vcpu);

        return map;
    }

    @Override
    public String getMeterName() {
        return "cloudstack.vm.cpu.hours";
    }

    //======= Setters and Getters

    public String getCpuspeed() {
        return cpuspeed;
    }

    public void setCpuspeed(String cpuspeed) {
        this.cpuspeed = cpuspeed;
    }

    public String getVcpu() {
        return vcpu;
    }

    public void setVcpu(String vcpu) {
        this.vcpu = vcpu;
    }

    public String getOfferingid() {
        return offeringid;
    }

    public void setOfferingid(String offeringid) {
        this.offeringid = offeringid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
