package io.nuvalence.au.grpcserver;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServerRunner {

    public static void main(String[] args) throws IOException, InterruptedException {

        KafkaCarMessageWriter.initializeKafkaProducer();

        int grpcport = 8080;
        Server server = ServerBuilder
                .forPort(grpcport)
                .addService(new CarServiceImpl()).build();

        server.start();

        System.out.println("******************************************");
        System.out.println(" gRPC Server listening in port " + grpcport);
        System.out.println("******************************************");

        server.awaitTermination();

    }

}
