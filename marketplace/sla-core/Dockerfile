#FROM maven:3-jdk-7
FROM maven:3.3.3-jdk-8-onbuild

WORKDIR /
ADD . /
RUN mvn install
#RUN mvn test exec:java -f sla-repository/pom.xml

ENV DB_URL=jdbc:mysql://mysql:3306/atossla
CMD ["/bin/sh","DockerStart.sh"]
