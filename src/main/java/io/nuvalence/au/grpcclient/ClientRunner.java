package io.nuvalence.au.grpcclient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.nuvalence.au.CarConfirmation;
import io.nuvalence.au.CarInfo;
import io.nuvalence.au.CarServiceGrpc;
import io.nuvalence.au.CarServiceGrpc.CarServiceBlockingStub;

public class ClientRunner {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();

        CarServiceBlockingStub stub = CarServiceGrpc.newBlockingStub(channel);

        CarInfo carInfo = CarInfo.newBuilder().setCarId(Long.parseLong(args[0])).setCarMessage(args[1]).build();

        CarConfirmation carConfResponse = stub.carTalking(carInfo);

        String serverResponseString = carConfResponse.getServerResponse();

        System.out.println("\nServer response:\n" + serverResponseString);

        channel.shutdown();

    }
}
