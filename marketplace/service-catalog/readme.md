# T-NOVA Busines Service Catalog #

This is a  REST service that provides a T-NOVA RESTful API for Busines Service Catalog.
For the persistence layer, it uses mongo database

## Build and Run Standalone ##

Startup MongoDb

```sh
mongod --dbpath /<database_data_path>/service-catalog
```

Startup Service Catalog Service using Maven:

```sh
mvn clean package spring-boot:run
```

Startup Service Catalog Service using command line:

```sh
mvn clean package
java -jar target/service-catalog-1.0.jar
```

## Docker Image Installation  ##

Enable docker configuration for mongodb & service catalog micro service. 

```sh
./start-service.sh
```

Stop micro-services and remove docker images
```sh
./stop-service.sh
```

##  API Documentation ##
The following [link](http://localhost:42050/swagger-ui.html), provides documentation regarding the RESTfull API Calls.
Note that this is auto generated, and you must start server to navigate in the api
