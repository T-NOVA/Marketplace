# Copyright (c) 2015. Zuercher Hochschule fuer Angewandte Wissenschaften
# All Rights Reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License"); you may
# not use this file except in compliance with the License. You may obtain
# a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
# WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
# License for the specific language governing permissions and limitations
# under the License.
#
# Author: Piyush Harsh,
# URL: piyush-harsh.info

FROM cyclops-base

EXPOSE 8080 8083 8086 8000 80 443 15672 5672

#prepping the environment for cyclops
RUN mkdir /var/log/cyclops/ && chmod 777 /var/log/cyclops/

#creating home folder for cyclops
RUN mkdir -p /home/cyclops/bills/ && chmod 777 /home/cyclops/bills/
RUN mkdir -p /home/cyclops/sqlite/ && chmod 777 /home/cyclops/sqlite/

#prepopulating the necessary sqlite3 tables
RUN sqlite3 /home/cyclops/sqlite/dashboard.db "CREATE TABLE IF NOT EXISTS meter_source (ID INTEGER PRIMARY KEY, source TEXT NOT NULL);";
RUN sqlite3 /home/cyclops/sqlite/dashboard.db "CREATE TABLE IF NOT EXISTS bills (ID INTEGER PRIMARY KEY, userId TEXT NOT NULL, billPDF TEXT NOT NULL, fromDate TEXT NOT NULL, toDate TEXT NOT NULL, approved BOOLEAN NOT NULL DEFAULT 0, paid BOOLEAN NOT NULL DEFAULT 0, dueDate TEXT NOT NULL, paymentDate TEXT, created TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
RUN sqlite3 /home/cyclops/sqlite/dashboard.db "CREATE TABLE IF NOT EXISTS external_id (ID INTEGER PRIMARY KEY, userId TEXT NOT NULL, meterSourceId INTEGER NOT NULL, meterUserId TEXT NOT NULL);";
RUN sqlite3 /home/cyclops/sqlite/dashboard.db "CREATE TABLE IF NOT EXISTS gatekeeper_users (username TEXT PRIMARY KEY, password TEXT NOT NULL, userId TEXT NOT NULL);";
RUN sqlite3 /home/cyclops/sqlite/dashboard.db "CREATE TABLE  IF NOT EXISTS dashboard_users (username TEXT PRIMARY KEY, password TEXT NOT NULL, name TEXT NOT NULL, surname TEXT NOT NULL, email TEXT NOT NULL, isAdmin BOOLEAN NOT NULL, keystoneId TEXT);";
RUN chmod 777 /home/cyclops/sqlite/dashboard.db

ADD aio-image.sh /root/aio-image.sh
ADD aio-build-wars.sh /root/aio-build-wars.sh
RUN chmod +x /root/aio-image.sh 
RUN chmod +x /root/aio-build-wars.sh
RUN ./root/aio-image.sh

#putting the configuration files in correct path#
ADD conf/rc-configuration.txt /home/cyclops/src/marketplace/cyclops/src/rc/src/main/webapp/WEB-INF/configuration.txt
ADD conf/udr-configuration.txt /home/cyclops/src/marketplace/cyclops/src/udr/src/main/webapp/WEB-INF/configuration.txt
ADD conf/billing-configuration.txt /home/cyclops/src/marketplace/cyclops/src/billing/src/main/webapp/WEB-INF/configuration.txt

#now building the wars and placing in correct folder#
RUN ./root/aio-build-wars.sh
