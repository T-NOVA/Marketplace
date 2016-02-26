# cyclops-aio (all in one docker image)

Pre-requisites: cyclops-base (image)

This guide demonstrates how to create the cyclops-aio docker image. Do-NOT modify any of the included bash scripts. Follow the steps mentioned below -

* update your stash credentials in ```aio-image.sh``` file.
* modify the individual cyclops micro-service configuration files first (these are located in the ```conf``` sub-folder). See details below on how to modify them properly.
  1. udr-configuration.txt
  2. rc-configuration.txt
  3. billing-configuration.txt
* Then simply execute the docker build command to create the ```cyclops-aio``` docker image

```
docker build -t cyclops-aio .
```
Once the ```cyclops-aio``` image has been successfully created, you can execute the cyclops-framework as a docker container using the following docker run command.

```
docker run --privileged=true --cap-add SYS_PTRACE -it -p 8080:8080 -p 8083:8083 -p 8086:8086 -p 15672:15672 -p 5672:5672 cyclops-aio /bin/bash
```
The details of individual service configuration file is shown next.

## udr micro-service configuration file
```
# Select the use case you want to work with (TNOVA, MCN, CLOUDSTACK, OPENSTACK)
Environment=Tnova

# If you are using internal scheduler, provide frequency in seconds
ScheduleFrequency=300

# In case of working with OpenStack
KeystoneURL=http://your-openstack-ip:5000/v2.0
KeystoneUsername=cyclops
KeystonePassword=tenant-password
KeystoneTenantName=services

# If the collector for OpenStack is Ceilometer
CeilometerURL=http://your-openstack-ip:8777/v2/

# Connection to the TimeSeries database
InfluxDBURL=http://localhost:8086
InfluxDBUsername=root
InfluxDBPassword=root
InfluxDBDatabaseName=udr_service

# Settings for RabbitMQ in case of event based usage
RabbitMQUsername=code
RabbitMQPassword=pass1234
RabbitMQHost=messaging.demonstrator.info
RabbitMQPort=5672
RabbitMQVirtualHost=/

# If you are working with CloudStack or Anolim's UsageService
CloudStackURL=https://swiss2.safeswisscloud.ch/client/api
CloudStackAPIKey=your-api-key
CloudStackSecretKey=your-secret-key
CloudStackPageSize=500
CloudStackDBLogsName=udr_logs
CloudStackEventMeasurement=cloudstack.events
CloudStackMeterListSelection=meterlist

# If you want to start importing only from this day, specify YYYY-MM-DD, if not, leave empty/delete line
#CloudStackFirstImport=2015-11-25

# Some MCN settings
MCNDBEventsName=mcn_events
MCNEventStart=start
MCNEventQueue=mcnevents

# And TNova settings
TNovaDBEventsName=event
TNovaEventStart=running
TNovaEventQueue=tnova_events
```
In this file you should update the rabbitmq values (if you have a separate rabbitmq service  configured elsewhere) where the accounting module sends the events. If you leave these values unchanged, the udr service will use the demonstration service created at ZHAW.

Change ```TNovaEventQueue``` to the queue name where the service status events are being sent to in the RabbitMQ setup. Rest all values you can leave unchanged.

## rc micro-service configuration file
```
#Cyclops RC Service configuration file

#Environment variables
Environment=Tnova

#Cyclops variables
UDRServiceUrl=http://localhost:8080/udr
RuleEngineUrl=http://localhost:8080/ruleengine/
RCServiceUrl=http://localhost:8080/rc

#Tnova variables
AccountingServiceUrl=http://160.85.4.190

#Scheduler variables
SchedulerFrequency=300

#InfluxDB variables
InfluxDBURL=http://localhost:8086
InfluxDBPassword=root
InfluxDBUsername=root
dbName=rc_service
eventsDbName=event
```
Change the ```AccountingServiceUrl``` value to the location (IP) where the TNova Accounting service is located. Rest of the values you can leave unchanged.

## billing micro-service configuration file
```
InfluxDBURL==http://localhost:8086
InfluxDBUsername==root
InfluxDBPassword==root
InfluxDBDatabaseName==billing_service
RcServiceUrl==http://localhost:8080/rc
```
The values in the billing micro-service configuration can be left untouched.

## Activating Cyclops
After starting the container using cyclops-aio image, you need to activate various micro-services once. This can be dene easily using following curl statements

```
curl -X GET http://container-ip:8080/udr/
curl -X GET http://container-ip:8080/rc/
curl -X GET http://container-ip:8080/billing/
```
Now you are all set to start using Cyclops in your business financial processing workflows. Happy Billing!

### Further Information
In case of further questions please contact - piyush.harsh@zhaw.ch

## Made by

<div align="left" >
<a href='http://blog.zhaw.ch/icclab'>
<img src="https://raw.githubusercontent.com/icclab/hurtle/master/docs/figs/icclab_logo.png" title="hurtle" width=320px border=0px>
</a>
</div>

(ASL v2.0) ICCLab / ZHAW