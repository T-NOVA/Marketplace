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

### getting udr code from stash ###
#mkdir -p /home/cyclops/src/
#cd /home/cyclops/src/
#git init
#git remote add origin http://piyush_harsh:ruchi84%@stash.i2cat.net/scm/TNOV/wp6.git
#git config core.sparsecheckout true
#echo marketplace/cyclops/src/ >> .git/info/sparse-checkout
#git pull origin master

### getting cyclops code from github ###
cd /home/cyclops/
git clone https://github.com/T-NOVA/cyclops.git
mv cyclops/src/ ./
rm -fR cyclops

### getting the influxdb-client library ###
cd
git clone https://github.com/influxdb/influxdb-java.git
cd influxdb-java
rm -fR src/test
mvn clean install -DskipTests=true
cd
rm -fR influxdb-java
