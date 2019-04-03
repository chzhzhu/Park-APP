package com.oracle.hackson.webapp.kafka.demo;

import com.oracle.hackson.webapp.simulation.DoHeartBeatCheck;
import com.oracle.hackson.webapp.simulation.ParkPort;
import com.oracle.hackson.webapp.simulation.Simulation;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;
import java.util.concurrent.ExecutionException;


public class ProducerDemo extends Thread{
    private KafkaProducer<String, ParkPort> producer;
    private final String topic;
    private final Boolean isAsync;
    private ParkPort parkPort;

    public ProducerDemo (String topic, Boolean isAsync) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKA_SERVER_URL + ":" + KafkaProperties.KAFKA_SERVER_PORT);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "DemoProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        this.topic = topic;
        this.isAsync = isAsync;
    }

    public ProducerDemo (String topic, Boolean isAsync, ParkPort parkPort) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKA_SERVER_URL + ":" + KafkaProperties.KAFKA_SERVER_PORT);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "DemoProducer");
        props.put("retries", 0);
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "com.oracle.hackson.webapp.Schema.ParkPortJosnSerializer");
        producer = new KafkaProducer<String, ParkPort>(props);
        this.topic = topic;
        this.isAsync = isAsync;
        this.parkPort = parkPort;
    }

    public void run() {


        while (true) {
            if (isAsync) { // Send asynchronously
                ProducerRecord<String, ParkPort> record = new ProducerRecord<String, ParkPort>(topic, String.valueOf(parkPort.getParkPortId()), parkPort);
                    producer.send(record);
                    try {
                        this.sleep(20000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            } else { // Send synchronously
                try {
                    ProducerRecord<String, ParkPort> record = new ProducerRecord<String, ParkPort>(topic, String.valueOf(parkPort.getParkPortId()), parkPort);
                        producer.send(record).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

class DemoCallBack implements Callback {

    private final long startTime;
    private final int key;
    private final String message;

    public DemoCallBack(long startTime, int key, String message) {
        this.startTime = startTime;
        this.key = key;
        this.message = message;
    }

    /**
     * A callback method the user can implement to provide asynchronous handling of request completion. This method will
     * be called when the record sent to the server has been acknowledged. When exception is not null in the callback,
     * metadata will contain the special -1 value for all fields except for topicPartition, which will be valid.
     *
     * @param metadata  The metadata for the record that was sent (i.e. the partition and offset). Null if an error
     *                  occurred.
     * @param exception The exception thrown during processing of this record. Null if no error occurred.
     */
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (metadata != null) {
            System.out.println(
                    "message(" + key + ", " + message + ") sent to partition(" + metadata.partition() +
                            "), " +
                            "offset(" + metadata.offset() + ") in " + elapsedTime + " ms");
        } else {
            exception.printStackTrace();
        }
    }
}
