#!/usr/bin/env bash

docker image build -t j-1 .
#-v /var/run/docker.sock:/var/run/docker.sock
docker run -p 8030:8080 -v //var/run/docker.sock:/var/run/docker.sock -v bkp_jenkins:/srv/backup j-1