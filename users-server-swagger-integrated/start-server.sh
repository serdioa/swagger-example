#!/bin/bash

JAR=$(/bin/find target -name users-server-swagger-integrated-*.jar)
if [[ -z "${JAR}" ]] ; then
    echo "Can not find executable jar" >&2
else
    java -jar ${JAR} "$@"
fi
