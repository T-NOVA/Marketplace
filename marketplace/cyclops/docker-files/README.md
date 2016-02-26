# cyclops containerization using Docker

This guide will show how to create the two docker images needed to be able to execute cyclops framework as a docker container process. The repository contains 2 folders namely cyclops-base and cyclops-aio (all-in-one).

The cyclops-base folder contains all necessary scripts to create the base file which is needed to derive the cyclops-aio image. Follow the steps in the README in that folder to create the cyclops-base image in your docker environment.

The cyclops-base image depends on ubuntu:14.04 standard docker image, so make sure that you have it in your environment.

Once the cyclops-base image is created, then proceed to build the cyclops-aio image by running the docker build command from the cyclops-aio folder. Follow the instructions in that folder.

(ASL v2.0) ICCLab / ZHAW