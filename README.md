# gRPC car service

## Important about Kafka config
Configure Kafka in file config/server.properties file (inside Kafka server)
with the following data replacing "theIPofMyKafkaServer" by the public/reachable ip of the Kafka server, and make sure firewall accepts input to that port.
> advertised.listeners=PLAINTEXT://theIPofMyKafkaServer:9092

## build with:
```bash
mvn clean compile assembly:single
```

jar with dependencies will be inside target ...
___
## running server and client:

then run server by running an instance of that jar as:
```bash
java -jar theResultingJar.jar
```

and run client by running a different instance of that jar as:
```bash
java -cp theResultingJar.jar io.nuvalence.au.grpcclient.ClientRunner [CarID] [CarMessage]
```

___
# Sources of understanding:
https://developers.google.com/protocol-buffers/docs/javatutorial

https://grpc.io/docs/languages/java/quickstart/

https://github.com/grpc/grpc-java/tree/master/examples/src/main/java/io/grpc/examples/helloworld

https://www.baeldung.com/grpc-introduction
