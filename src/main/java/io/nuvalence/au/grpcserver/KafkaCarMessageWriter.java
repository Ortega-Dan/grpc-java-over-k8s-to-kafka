package io.nuvalence.au.grpcserver;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaCarMessageWriter {

    public static Producer<String, String> producer;

    public static void initializeKafkaProducer() {
        Properties props = new Properties();
        // props.put("bootstrap.servers", "192.168.0.14:9092");
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(props);
    }

    public static void pushToKafkaTopic(String topic, long carId, String carMessage) {

        producer.send(new ProducerRecord<String, String>(topic, "" + carId, "" + carId + ": " + carMessage));

    }

}
