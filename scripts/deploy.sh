#!/bin/bash

REPOSITORY=/home/ubuntu/u-life-kurly/server

echo "> Copy build files"

cp $REPOSITORY/server/*.jar $REPOSITORY/

echo "> Checking pid of currently running application"

CURRENT_PID=$(pgrep -fl server | grep java | awk '{print $1}')

echo "Current application's pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
    echo "> The application is not currently running, so it will not be shut down."
else
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
fi

echo "> Deploy new application"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> chmod + x $JAR_NAME"

chmod +x $JAR_NAME

echo "> Execute $JAR_NAME "

nohup java -jar $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
