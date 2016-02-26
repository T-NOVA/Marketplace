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
package ch.icclab.cyclops.application;

import ch.icclab.cyclops.resource.client.InfluxDbClient;
import ch.icclab.cyclops.resource.impl.*;
import ch.icclab.cyclops.util.Load;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 * Author: Srikanta
 * Created on: 26-May-15
 * Description: The application class which acts as a router to service the incoming API request
 */
public class BillingServiceApplication extends Application {
    final static Logger logger = LogManager.getLogger(BillingServiceApplication.class.getName());

    /**
     * This method handles the incoming request and routes it to the appropriate resource class
     * <p/>
     * Pseudo code
     * 1. Create an instance of Router
     * 2. Attach the api end points and their respective resource class for request handling
     * 3. Return the router
     *
     * @return Restlet
     */
    public Restlet createInboundRoot() {
        loadConfiguration(getContext());

        Router router = new Router();
        router.attach("/", RootResource.class);
        router.attach("/invoice", BillResource.class);
        router.attach("/revenue", RevenueSharingReportResource.class);
        router.attach("/bill", TnovaBillResource.class);

        initialiseDatabases();

        return router;
    }

    /**
     * Loads the configuration file at the beginning of the application startup
     * <p/>
     * Pseudo Code
     * 1. Create the LoadConfiguration class
     * 2. Load the file if the the existing instance of the class is empty
     *
     * @param context
     */
    private void loadConfiguration(Context context) {
        Load load = new Load();
        if (load.configuration == null) {
            load.configFile(getContext());
        }
    }

    public void initialiseDatabases() {
        logger.debug("Initializing Databases.");
        InfluxDbClient dbClient = new InfluxDbClient();
        dbClient.createDatabases(Load.configuration.get("dbName"));
    }

}
