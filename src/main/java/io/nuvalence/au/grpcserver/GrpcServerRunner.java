package io.nuvalence.au.grpcserver;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServerRunner {

    public static void main(String[] args) throws IOException, InterruptedException {

        int port = 8080;

        Server server = ServerBuilder
                .forPort(port)
                .addService(new CarServiceImpl()).build();

        server.start();
        System.out.println("Server listening in port " + port);
        server.awaitTermination();

    }

}
