#! /bin/bash
bash buildClientAndServerJar.sh

eval $(minikube -p minikube docker-env)
docker build . -t test/grpccars