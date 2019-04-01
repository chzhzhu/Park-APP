package com.oracle.hackson.webapp.kafka.demo;

public class KafkaStart {
    public static void main(String[] args) {
//        boolean isAsync = args.length == 0 || !args[0].trim().equalsIgnoreCase("sync");
//        ProducerDemo producerThread = new ProducerDemo(KafkaProperties.TOPIC, isAsync);
//        producerThread.start();

        ConsumerDemo consumerThread = new ConsumerDemo(KafkaProperties.TOPIC);
        consumerThread.start();

    }
}
