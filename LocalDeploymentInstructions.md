
If creating a minikube for the first time start it with:
```bash
minikube start --memory 8192 --cpus 4
```
otherwise with:
```bash
minikube start
```

Everything from the same terminal from here

Before building the image run:

```bash
eval $(minikube -p minikube docker-env)
```

Then having the needed Dockerfile, build your docker image from the root of the repo with:
```bash
docker build . -t yourChosenPrefix/yourDesiredImageName
```

Then check if minikube's docker has the build image with:
```bash
minikube image ls
```
must show something like: docker.io/yourChosenPrefix/yourDesiredImageName:latest

Then create an automated yaml deployment file with:
```bash
kubectl create deployment youChosenK8sDeploymentName --image=docker.io/yourChosenPrefix/yourDesiredImageName:latest -o yaml --dry-run=client > myFile.yml
```
Then at the container level very near the end add the "imagePullPolicy: Never" attribute.

And then deploy from the yaml file with
```bash
kubectl apply -f myFile.yml
```
Check if working well with:
```bash
kubectl get deployments
```
and
```bash
kubectl get pods
```
Expose it as a service with (also change port as needed):
```bash
kubectl expose deployment youChosenK8sDeploymentName --type=LoadBalancer --port=8080
```

Make sure it is exposed with:
```bash
kubectl get services
```
Find your local service running ip with:
```bash
minikube service list
```