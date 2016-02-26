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
package ch.icclab.cyclops.resource.client;

import ch.icclab.cyclops.util.Load;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;

/**
 * @author Manu
 *         Created on 18.01.16.
 */
public class InfluxDbClient {
    private String url;
    private String username;
    private String password;
    private String dbName;

    public InfluxDbClient() {
        this.url = Load.configuration.get("InfluxDBURL");
        this.username = Load.configuration.get("InfluxDBUsername");
        this.password = Load.configuration.get("InfluxDBPassword");
        this.dbName = Load.configuration.get("dbName");
    }

    /**
     * Create databases based on list of names
     *
     * @param names for database creation
     */
    public void createDatabases(String... names) {
        InfluxDB client = getConnection();

        // now create required databases
        for (String name : names) {
            client.createDatabase(name);
        }
    }

    /**
     * Save container to InfluxDB container
     *
     * @param container that is goint to be saved
     */
    public void saveContainerToDB(BatchPoints container) {
        InfluxDB db = getConnection();
        db.write(container);
    }

    /**
     * Ask for connection to InfluxDB
     *
     * @return
     */
    private InfluxDB getConnection() {
        return InfluxDBFactory.connect(this.url, this.username, this.password);
    }

    /**
     * Asks for InfluxDB BatchPoints container
     *
     * @return empty container
     */
    public BatchPoints giveMeEmptyContainer() {
        return BatchPoints
                .database(dbName)
                .retentionPolicy("default")
                .consistency(InfluxDB.ConsistencyLevel.ALL)
                .build();
    }
}
