package io.nuvalence.au.grpcserver;

import io.grpc.stub.StreamObserver;
import io.nuvalence.au.CarConfirmation;
import io.nuvalence.au.CarInfo;
import io.nuvalence.au.CarServiceGrpc.CarServiceImplBase;

public class CarServiceImpl extends CarServiceImplBase {



    @Override
    public void carTalking(CarInfo request, StreamObserver<CarConfirmation> responseObserver) {

        System.out.println("gRPC Service is being called !!");
        System.out.println("Data: ");
        System.out.println("CarID: " + request.getCarId());
        System.out.println("CarMessage: " + request.getCarMessage() + "\n");

        CarConfirmation carConfirm = CarConfirmation.newBuilder().setServerResponse(
                "Car " + request.getCarId() + ". Your message (" + request.getCarMessage()
                        + ") has been received and piped to Kafka !!")
                .build();

        KafkaCarMessageWriter.pushToKafkaTopic("car-message-events", request.getCarId(), request.getCarMessage());

        responseObserver.onNext(carConfirm);
        responseObserver.onCompleted();

    }

}
