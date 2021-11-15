#!/bin/bash

# mvn package spring-boot:repackage

JAR=$(/bin/find target -name users-server-openapi-integrated-*.jar)
if [[ -z "${JAR}" ]] ; then
    echo "Can not find executable jar" >&2
else
    java -jar ${JAR} "$@"
fi
