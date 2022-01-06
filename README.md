# build with:
```bash
mvn clean compile assembly:single
```

jar with dependencies will be inside target ...

then run server by running an instance of that jar as:
```bash
java -jar theResultingJar.jar
```

and run client by running an different instance of that jar as:
```bash
java -cp theResultingJar.jar io.nuvalence.au.grpcclient.ClientRunner [CarID] [CarMessage]
```