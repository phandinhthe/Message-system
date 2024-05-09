#!/bin/sh
mvn clean package && clear && java -jar target/message-system-1.0-SNAPSHOT.jar
