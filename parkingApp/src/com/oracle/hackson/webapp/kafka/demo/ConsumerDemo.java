package com.oracle.hackson.webapp.kafka.demo;

import com.oracle.hackson.webapp.simulation.ParkPort;
import com.oracle.hackson.webapp.util.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.util.Collections;
import java.util.Properties;

public class ConsumerDemo extends ShutdownableThread{
    private KafkaConsumer<String, ParkPort> consumer;
    private final String topic;

    public ConsumerDemo(String topic) {
        super("KafkaConsumerExample", false);
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKA_SERVER_URL + ":" + KafkaProperties.KAFKA_SERVER_PORT);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "DemoConsumer");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.oracle.hackson.webapp.Schema.ParkPortJosnDeserializer");

        consumer= new KafkaConsumer<>(props);

        this.topic = topic;
    }

    @Override
    public void doWork() {
        consumer.subscribe(Collections.singletonList(this.topic));
        ConsumerRecords<String, ParkPort> records = consumer.poll(1000);
        for (ConsumerRecord<String, ParkPort> record : records) {
            System.out.println("Received message: " + record.value().getEquId() + "'s alive statue is " + record.value().isAlive() + ", at offset " + record.offset());
        }
    }


}


