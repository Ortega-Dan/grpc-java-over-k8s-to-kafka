bash buildLocalK8sContainer.sh

kubectl delete service grpccars
kubectl delete deployment grpccars
eval $(minikube -p minikube docker-env)
kubectl apply -f kubernetesDeployment.yml
kubectl expose deployment grpccars --type=LoadBalancer --port=8080
minikube service list