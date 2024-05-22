package org.toolingminds.sunburn.executable.persist;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;
import java.util.regex.Pattern;

public class Persist {
    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = getConsumer();
        boolean running = isRunning();
        consumer.subscribe(Pattern.compile("track"));
        while(running) {
            consumer.poll(1000).forEach(record -> {
                System.out.println("Received record: " + record.value());
            });
            running = isRunning();
        }

    }

    private static KafkaConsumer<String, String> getConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", System.getenv("KAFKA_BOOTSTRAP_SERVERS"));
        props.put("group.id", "sunburn");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return new KafkaConsumer<>(props);
    }

    private static boolean isRunning() {
        //TODO: enable graceful termination
        return true;
    }
}
