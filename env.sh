#!/bin/bash

if [ "$1" = "start" ] ; then

  echo "Building Postgres container...."
  docker run --name db-rekolekcje -p 5430:5432 -e POSTGRES_DB=rekolekcjedb -e POSTGRES_PASSWORD=postgres -d postgres
  echo "DB can be accesed via localhost:5430"

  echo "Building Postres container for integration tests..."
  docker run --name db-rekolekcje-test -p 5431:5432 -e POSTGRES_DB=rekolekcjedb-test -e POSTGRES_PASSWORD=postgres -d postgres
  echo "Test DB can be accesed via localhost:5431"

elif [ "$1" = "wipe" ] ; then

  echo "Stopping all running containers..."
  docker stop $(docker ps -a -q)

  echo "Removing all stopped containers..."
  docker rm $(docker ps -a -q)

else
  echo "Invalid command. Type './env.sh start' or './env.sh wipe'."
  exit 0

fi