package com.oracle.hackson.webapp.kafka.demo;


import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import com.oracle.hackson.webapp.java.main.MongoDBUtils;
import com.oracle.hackson.webapp.simulation.Equipment;
import com.oracle.hackson.webapp.simulation.ParkPort;
import com.oracle.hackson.webapp.util.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.bson.Document;

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
        Gson gson = new Gson();
        for (ConsumerRecord<String, ParkPort> record : records) {
            String parkPortStr = gson.toJson(record.value());
            BasicDBObject dbObjectPark = (BasicDBObject)JSON.parse(parkPortStr);
            Document documentPark = new Document(dbObjectPark.toMap());
            String parkPortQuery = MongoDBUtils.findByPortId(KafkaProperties.DATABASE,KafkaProperties.PARKPORT_COL,Integer.valueOf(record.key()));
            if (parkPortQuery == null){
                MongoDBUtils.insert(KafkaProperties.DATABASE,KafkaProperties.PARKPORT_COL,documentPark);
            } else {
                String equJson = gson.toJson(record.value());
                System.out.println(equJson);
//                Document parkDocument = MongoDBUtils.updateDocument(equJson);
//                MongoDBUtils.updateIntField(KafkaProperties.DATABASE,KafkaProperties.PARKPORT_COL,"parkPortId",record.key(),parkDocument);
            }
            for (Equipment e : record.value().equ) {
                System.out.println("Received message: " + e.getEquId() + "'s alive statue is " + e.isAlive() + ", at offset " + record.offset());
                String equQuery = MongoDBUtils.findEquByEquId(KafkaProperties.DATABASE,KafkaProperties.EQUIPMENT_COL,e.getEquId());
                if(equQuery == null) {
                    String equStr = gson.toJson(e);
                    BasicDBObject dbObjectEqu = (BasicDBObject)JSON.parse(equStr);
                    Document equDocument= new Document(dbObjectEqu.toMap());
                    MongoDBUtils.insert(KafkaProperties.DATABASE, KafkaProperties.EQUIPMENT_COL, equDocument);
                } else{
                    String equStr = gson.toJson(e);
                    System.out.println(equStr);
//                    Document equDocument = MongoDBUtils.updateDocument(equStr);
//                    MongoDBUtils.updateIntField(KafkaProperties.DATABASE,KafkaProperties.EQUIPMENT_COL,"equId",String.valueOf(e.getEquId()),equDocument);
                }
            }

        }
    }

}


