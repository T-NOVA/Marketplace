#!/bin/bash
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

#building udr war#
#cd /home/cyclops/src/marketplace/cyclops/src/udr/
cd /home/cyclops/src/udr/
mvn clean install -DskipTests=true
cp target/*.war /var/lib/tomcat7/webapps/udr.war

#building rc war#
#cd /home/cyclops/src/marketplace/cyclops/src/rc/
cd /home/cyclops/src/rc/
mvn clean install -DskipTests=true
cp target/*.war /var/lib/tomcat7/webapps/rc.war

#building billing war#
#cd /home/cyclops/src/marketplace/cyclops/src/billing/
cd /home/cyclops/src/billing/
mvn clean install -DskipTests=true
cp target/*.war /var/lib/tomcat7/webapps/billing.war
