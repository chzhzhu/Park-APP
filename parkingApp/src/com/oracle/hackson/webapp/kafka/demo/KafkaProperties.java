package com.oracle.hackson.webapp.kafka.demo;

public class KafkaProperties {
    public static final String TOPIC = "topic3";
    public static final String[] TOPICS = {"topic10","topic11","topic12","topic13","topic14","topic15","topic16","topic17","topic18","topic19"};
    public static final String KAFKA_SERVER_URL = "localhost";
    public static final int KAFKA_SERVER_PORT = 9092;
    public static final int KAFKA_PRODUCER_BUFFER_SIZE = 64 * 1024;
    public static final int CONNECTION_TIMEOUT = 100000;
    public static final String CLIENT_ID = "SimpleConsumerDemoClient";
    public static final String DATABASE = "parkingApp";
    public static final String PARKPORT_COL = "parkport";
    public static final String EQUIPMENT_COL = "equipment";

    private KafkaProperties() {}
}
