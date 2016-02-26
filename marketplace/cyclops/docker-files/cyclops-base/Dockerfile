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
#
# Thanks to: https://github.com/Maluuba/docker-files with tomcat7 scripts

FROM ubuntu:14.04

EXPOSE 8080 8083 8086 80 443 15672 5672

RUN apt-get install -y software-properties-common && add-apt-repository -y ppa:webupd8team/java && apt-get -y update && apt-get -y upgrade && apt-get install -y wget && apt-get install -y nano curl libcurl3 libcurl3-dev
RUN echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections && apt-get install -y oracle-java7-installer && update-alternatives --display java && echo "JAVA_HOME=/usr/lib/jvm/java-7-oracle" >> /etc/environment

RUN apt-get remove -y maven2 && apt-get install -y maven
# RUN apt-get install -y jetty libjetty8-extra-java libjetty8-java libjetty-extra-java libjetty-extra libjetty-java-doc jetty8 libjetty8-java-doc libjetty-java jsvc apache2-utils adduser
RUN apt-get -y install tomcat7 && apt-get -y install tomcat7-docs tomcat7-admin tomcat7-examples && echo "JAVA_HOME=/usr/lib/jvm/java-7-oracle" >> /etc/default/tomcat7
RUN apt-get -y install ant git sqlite
RUN apt-get install -y rabbitmq-server
RUN rabbitmq-plugins enable rabbitmq_management

ADD start-tomcat.sh /opt/start-tomcat.sh
RUN chmod +x /opt/start-tomcat.sh
RUN mv /etc/cron.daily/logrotate /etc/cron.hourly/logrotate
ADD logrotate /etc/logrotate.d/tomcat7
RUN chmod 644 /etc/logrotate.d/tomcat7
RUN locale-gen en_US.UTF-8
ENV LANG en_US.UTF-8 
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8 

RUN wget https://raw.githubusercontent.com/icclab/cyclops-support/gatekeeper/scripts/pre-req.sh
RUN wget https://raw.githubusercontent.com/icclab/cyclops-support/gatekeeper/scripts/cyclopsudr.sh
RUN wget https://raw.githubusercontent.com/icclab/cyclops-support/gatekeeper/scripts/cyclopsrc.sh
RUN wget https://raw.githubusercontent.com/icclab/cyclops-support/gatekeeper/scripts/cyclopsbilling.sh
RUN wget https://github.com/icclab/cyclops-support/blob/gatekeeper/scripts/cyclopsdashboard.sh
RUN chmod +x *.sh
RUN wget http://influxdb.s3.amazonaws.com/influxdb_0.9.4.1_amd64.deb
RUN dpkg -i influxdb_0.9.4.1_amd64.deb
RUN /etc/init.d/influxdb restart

ENTRYPOINT ["/opt/start-tomcat.sh"]
