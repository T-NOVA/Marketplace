#
# Usage: $0 [port]
#   port: listening port; defaults to 8080
#
#PORT=${1:-8080}
#mvn tomcat:run -f sla-service/pom.xml -Dmaven.tomcat.port=$PORT

PORT=${1:-9040}
#mvn tomcat:run -f sla-service/pom.xml -Dmaven.tomcat.port=$PORT
java -jar sla-service/target/dependency/jetty-runner.jar --port $PORT --path / sla-service/target/sla-service.war

