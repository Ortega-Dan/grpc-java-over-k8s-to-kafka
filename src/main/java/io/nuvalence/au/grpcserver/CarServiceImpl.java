package io.nuvalence.au.grpcserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.stub.StreamObserver;
import io.nuvalence.au.CarConfirmation;
import io.nuvalence.au.CarInfo;
import io.nuvalence.au.CarServiceGrpc.CarServiceImplBase;

public class CarServiceImpl extends CarServiceImplBase {

    private final static Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Override
    public void carTalking(CarInfo request, StreamObserver<CarConfirmation> responseObserver) {

        logger.info("gRPC Service is being called !!");
        logger.info("Data: ");
        logger.info("CarID: " + request.getCarId());
        logger.info("CarMessage: " + request.getCarMessage());
        logger.info("(piping message to Kafka)");

        CarConfirmation carConfirm = CarConfirmation.newBuilder().setServerResponse(
                "Car " + request.getCarId() + ". Your message (" + request.getCarMessage()
                        + ") has been received and piped to Kafka !!")
                .build();

        KafkaCarMessageWriter.pushToKafkaTopic("car-message-events", request.getCarId(), request.getCarMessage());

        responseObserver.onNext(carConfirm);
        responseObserver.onCompleted();

        logger.info("Message successfully posted to Kafka\n");
    }

}
