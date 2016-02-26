## Cyclops Base Image Dockerfile
The included Dockerfile and other scripts allow anyone to create the base docker container image with software prerequisites required by cyclops framework. The following packages and services are included -

* Java 7.0 runime
* maven 3.0.x
* tomcat 7.0
* influxdb 0.9.3
* sqlite3
* git
* curl

To create the container image from your docker context simply run the first command shown below. If you wish to create and execute the container using the newly created docker image, simply run the 2nd command shown below.

```
docker build -t cyclops-base .
docker run --cap-add SYS_PTRACE -it -p 8080:8080 -p 8083:8083 -p 8086:8086 -p 15672:15672 -p 5672:5672 cyclops-base /bin/bash
```
Queries & Questions - piyush.harsh@zhaw.ch

(ASL v2.0) ICCLab / ZHAW
