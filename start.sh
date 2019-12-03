#!/bin/bash

if [ $# = 0 ]; then
    echo "You must provide an argument. 'user' or 'chill' "
    exit 1;
fi


echo "Environnement $1 is running ....."

if [ $1 = "user" ]; then
    docker-compose up --build user_management user_db
fi

if [ $1 = "chill" ]; then
    docker-compose up --build chill chill_db
fi


