#!/bin/bash

LOG_DIR=target/log
GC_LOG=${LOG_DIR}/gc.log

# JAVA_OPTIONS="-XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:+PrintFlagsFinal
#    -Xloggc:${GC_LOG} -XX:+PrintGCDetails -XX:+PrintTenuringDistribution -XX:+PrintGCDateStamps
#    -XX:+UseConcMarkSweepGC
#    "

#JAVA_OPTIONS="-XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:+PrintFlagsFinal
#    -Xloggc:${GC_LOG} -XX:+PrintGCDetails -XX:+PrintTenuringDistribution -XX:+PrintGCDateStamps
#    -XX:+UseG1GC
#    "

mkdir -p ${LOG_DIR}

# Run from Maven
# mvn spring-boot:run -Dspring.config.location=file:src/config/rest-server.properties "$@"

# Package as runnable JAR and start
# mvn package spring-boot:repackage
# java -Dspring.config.location=file:src/config/rest-server.properties -jar target/rest-server-1.0-SNAPSHOT.jar "$@"
# java -Dspring.config.location=file:src/config/rest-server_8081.properties -jar target/rest-server-1.0-SNAPSHOT.jar "$@"
# java -Dspring.config.location=file:src/config/rest-server_8082.properties -jar target/rest-server-1.0-SNAPSHOT.jar "$@"

#     --spring.config.location=file:src/config/rest-server.properties \


mvn package spring-boot:repackage
java -jar target/users-server-swagger-codegen-rest-1.0-SNAPSHOT.jar \
    "$@"

