package org.toolingminds.sunburn.emitter;

import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaEmitter extends Emitter {
    static org.apache.kafka.clients.producer.KafkaProducer<String, String> producer;

    KafkaEmitter() {
        if(producer== null) {
            Properties props = new Properties();
            String bootstrapServers = System.getenv("KAFKA_BOOTSTRAP_SERVERS");
            if(bootstrapServers == null)
                throw new RuntimeException("KAFKA_BOOTSTRAP_SERVERS environment variable is null.");
            props.put("bootstrap.servers", System.getenv("KAFKA_BOOTSTRAP_SERVERS"));
            props.put("acks", "0");
//            props.put("retries", 0);
//            props.put("batch.size", 16384);
//            props.put("linger.ms", 1);
            props.put("buffer.memory", 33554432);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

            producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);
        } else {
            System.out.println("Grumble grumble - producer already exists!");
        }
    }

    public void send(String topic, String message) {
        producer.send(new ProducerRecord<>(topic, message));
    }

    public static void close() {
        producer.close();
    }
}
