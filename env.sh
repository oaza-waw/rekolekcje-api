#!/bin/bash

if [ "$1" = "start" ] ; then

  echo "Building Postgres container...."
  docker run --name db-rekolekcje -p 5430:5432 -e POSTGRES_DB=rekolekcjedb -e POSTGRES_PASSWORD=postgres -d postgres

  echo "Building Postres container for integration tests..."
  docker run --name db-rekolekcje-test -p 5433:5432 -e POSTGRES_DB=rekolekcjedb-test -e POSTGRES_PASSWORD=mysecretpassword -d postgres

elif [ "$1" = "wipe" ] ; then

  echo "Stopping all running containers..."
  docker stop $(docker ps -a -q)

  echo "Removing all stopped containers..."
  docker rm $(docker ps -a -q)

else
  echo "Invalid command. Type './env.sh start' or './env.sh wipe'."
  exit 0

fi