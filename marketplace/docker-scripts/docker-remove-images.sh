#!/bin/sh
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker rmi $(docker images | grep 'marketplace' | awk {'print $3'})