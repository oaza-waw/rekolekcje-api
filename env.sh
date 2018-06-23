#!/bin/bash

if [ "$1" = "start" ] ; then

  echo "Building Postgres container...."
  docker run --name db-rekolekcje -p 5432:5432 -e POSTGRES_DB=rekolekcjedb -e POSTGRES_PASSWORD=postgres -d postgres

elif [ "$1" = "wipe" ] ; then

  echo "Stopping all running containers..."
  docker stop $(docker ps -a -q)

  echo "Removing all stopped containers..."
  docker rm $(docker ps -a -q)

fi