#!/bin/sh

./wait-for -t 45 db:3306
java -jar target/si-0.0.1-SNAPSHOT.jar

