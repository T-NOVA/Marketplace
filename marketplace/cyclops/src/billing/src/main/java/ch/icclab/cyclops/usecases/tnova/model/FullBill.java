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

/**
 * @author Manu
 *         Created on 09.12.15.
 */
public class FullBill {
    private String userId;
    private String from;
    private String to;
    private RevenueSharingFullEntry[] charges;

    public String getUserId() {
        return userId;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public RevenueSharingFullEntry[] getCharges() {
        return charges;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setCharges(RevenueSharingFullEntry[] charges) {
        this.charges = charges;
    }

}