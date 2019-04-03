package com.oracle.hackson.webapp.kafka.demo;

import com.google.gson.Gson;
import com.oracle.hackson.webapp.kafka.connect.mongodb.MongodbSinkTask;
import com.oracle.hackson.webapp.simulation.Equipment;
import com.oracle.hackson.webapp.simulation.ParkPort;
import com.oracle.hackson.webapp.util.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.sink.SinkRecord;

import java.nio.charset.StandardCharsets;
import java.util.*;

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
        MongodbSinkTask mongodbSinkTask = new MongodbSinkTask();
        Map<String, String> sourceProperties = getSourceProperties();
        //mongodbSinkTask.start(sourceProperties);

        for (ConsumerRecord<String, ParkPort> record : records) {
            Gson gson = new Gson();
            String str = new String(gson.toJson(record.value()).getBytes(), StandardCharsets.UTF_8);
            //SinkRecord sinkRecord = new SinkRecord(this.topic,0,null,null, Schema.STRING_SCHEMA,str,record.offset());
            //mongodbSinkTask.put(Arrays.asList(sinkRecord));
            for (Equipment e : record.value().equ) {
                System.out.println("Received message: " + e.getEquId() + "'s alive statue is " + e.isAlive() + ", at offset " + record.offset());
            }
        }
    }

    public Map<String, String> getSourceProperties () {
        Map<String, String> sourceProperties = new HashMap<String, String>();
        sourceProperties.put("host", "slc09ybj.us.oracle.com");
        sourceProperties.put("port", Integer.toString(27017));
        sourceProperties.put("bulk.size", Integer.toString(1000));
        sourceProperties.put("mongodb.collections", "parkport");
        sourceProperties.put("topics", "topic2");
        sourceProperties.put("mongodb.database", "parkingApp");
        return sourceProperties;
    }


}


