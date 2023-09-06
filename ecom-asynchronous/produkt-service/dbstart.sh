#!/bin/bash

docker-compose up -d

sleep 5

sudo docker exec mongo1 /scripts/rs-init.shDana