package io.nuvalence.au.grpcserver;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServerRunner {

    private final static Logger logger = LoggerFactory.getLogger(GrpcServerRunner.class);

    public static void main(String[] args) throws IOException, InterruptedException {

        KafkaCarMessageWriter.initializeKafkaProducer();

        int grpcport = 8080;
        Server server = ServerBuilder
                .forPort(grpcport)
                .addService(new CarServiceImpl())
                .intercept(new LoggingInterceptor())
                .build();

        server.start();

        logger.info("******************************************");
        logger.info(" gRPC Server listening in port " + grpcport);
        logger.info("******************************************");

        server.awaitTermination();

    }

}
