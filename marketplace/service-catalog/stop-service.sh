#!/usr/bin/env bash

echo "$(date +'%b-%d-%y, %H:%M:%S') - INFO: Stopping/Removing service-catalog docker image"
docker stop servicecatalog_mongodb_1 && docker rm servicecatalog_mongodb_1


echo "$(date +'%b-%d-%y, %H:%M:%S') - INFO: Stopping/Removing mongod docker image"
docker stop servicecatalog_service-catalog_1 && docker rm servicecatalog_service-catalog_1



