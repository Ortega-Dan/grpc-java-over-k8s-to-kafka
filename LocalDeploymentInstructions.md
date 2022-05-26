
If creating a minikube with a single node for the first time start it with:
```bash
minikube start --memory 8192 --cpus 4
```
or just:
```bash
minikube start
```
or create a multi-node cluster using the -n option and the number of desired nodes like:
```bash
minikube start -n 3
```


Before building the image run:

```bash
eval $(minikube -p minikube docker-env)
```
(this won't be needed [and won't work] with a minikube multi-node cluster)

Then having the needed Dockerfile, build your docker image from the root of the repo with (assuming the runner app is ready, like a built uber-jar in the case of Java):
```bash
docker build . -t yourChosenPrefix/yourDesiredImageName
```

Then check if minikube's docker has the built image with:
```bash
minikube image ls
```
it must show something like: `docker.io/yourChosenPrefix/yourDesiredImageName:latest`

If it is a multi-node cluster, it was surely built into the local docker registry. To find it in the local docker registry do:
```bash
docker images
```
if there, load it to minikube with:
```bash
minikube image load yourChosenPrefix/yourDesiredImageName:latest
```
(the last part can be "latest" or the version or tag placed for it in the docker registry see in `docker images` output)\
(and check again if loaded to minikube with: `minikube image ls`)


Then create an automated yaml deployment file with:
```bash
kubectl create deployment youChosenK8sDeploymentName --image=docker.io/yourChosenPrefix/yourDesiredImageName:latest -o yaml --dry-run=client > myFile.yml
```
Then at the "containers" level very near the end add the "imagePullPolicy: Never" attribute.

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
or just as a single port exposure:
```bash
kubectl expose deployment youChosenK8sDeploymentName --type=NodePort --port=8080
```

Make sure it is exposed with:
```bash
kubectl get services
```
Find your local service running ip with:
```bash
minikube service list
```

Read pod logs with:
```bash
kubectl logs -f theResultingNameOfThePod
```
(get the name of the pod with: kubectl get pods)