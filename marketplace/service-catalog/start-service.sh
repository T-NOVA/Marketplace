#!/usr/bin/env bash

#
# Project     : t-nova Service catalog startup script
# Filename    : status_check_ta.sh
# Description : This  script generates alarms in case of hard
#               drive failures and ethernets links down.
##
#
# For debugging the script, uncomment the following line
#set -x
#
BUILD_DIR=build

echo "$(date +'%b-%d-%y, %H:%M:%S') - INFO: Check if docker-machine is available"
DOCKER_MACHINE=`docker-machine ls | grep t-nova | awk '{print $1}'`

if [ "$DOCKER_MACHINE" = "t-nova" ]; then
    echo "$(date +'%b-%d-%y, %H:%M:%S') - INFO: Check the machine name is ${DOCKER_MACHINE}"
else
    echo "$(date +'%b-%d-%y, %H:%M:%S') - INFO: Create the machine name with ${DOCKER_MACHINE}"
    docker-machine create --driver virtualbox t-nova
fi

echo "$(date +'%b-%d-%y, %H:%M:%S') - INFO: Seting up environment"
eval "$(docker-machine env t-nova)"

export DOCKER_HOST_IP=`docker-machine ip t-nova`
export SPRING_DATA_MONGODB_URI=mongodb://${DOCKER_HOST_IP}

echo "$(date +'%b-%d-%y, %H:%M:%S') - INFO: Create the mongodb docker image"
docker-compose up -d --no-recreate mongodb

echo "$(date +'%b-%d-%y, %H:%M:%S') - INFO: Building service catalog micro-service"
mvn clean package

if [[ ! -e $BUILD_DIR ]]; then
    mkdir $BUILD_DIR
elif [[ ! -d $BUILD_DIR ]]; then
    echo "$BUILD_DIR already exists but is not a directory" 1>&2
fi

echo "$(date +'%b-%d-%y, %H:%M:%S') - INFO: Create the service-catalog docker image"
docker-compose up -d --no-deps service-catalog

echo "$(date +'%b-%d-%y, %H:%M:%S') - INFO: Eat my dust .. I have created all"
docker ps
echo "$(date +'%b-%d-%y, %H:%M:%S') - INFO: Microservice end point: http://$DOCKER_HOST_IP:42050/service/catalog"
