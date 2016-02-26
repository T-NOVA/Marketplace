FROM maven:3.3.3-jdk-8

ADD . /opt
ADD bin/service-catalog-1.0.jar /opt/app.jar
ADD bin/application.properties /opt/application.properties
RUN bash -c 'touch /opt/app.jar'

ENTRYPOINT ["java","-jar","/opt/app.jar", "--spring.config.location=/opt/application.properties"]


