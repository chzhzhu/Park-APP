package com.oracle.hackson.webapp.simulation;

import java.util.TimerTask;

import com.oracle.hackson.webapp.kafka.demo.KafkaProperties;
import com.oracle.hackson.webapp.kafka.demo.ProducerDemo;
import com.oracle.hackson.webapp.kafka.demo.ConsumerDemo;

public class DoHeartBeatCheck extends TimerTask{
    public int TIMEOUT_COUNT = 5;
    ParkPort parkPort;

    public DoHeartBeatCheck(ParkPort p) {
        this.parkPort = p;
    }
    public void run() {
        ProducerDemo producerThread = new ProducerDemo(KafkaProperties.TOPIC, true, parkPort);
        ConsumerDemo consumerThread = new ConsumerDemo(com.oracle.hackson.webapp.kafka.demo.KafkaProperties.TOPIC);
        for (Equipment e : parkPort.equ) {
            if (e.ping.reset) {
                e.ping.reset = false;
                e.ping.count = 0;
                System.out.println(e.equId + " is up.");
            } else if (!e.ping.reset) {
                if (e.ping.count >= TIMEOUT_COUNT) {
                    e.setAlive(false);// timeout, set disable
                    System.out.println("Waiting for " + e.equId + " heartbeat.");
                } else {
                    ++e.ping.count;
                    System.out.println(e.equId + " is down");
                }
            }
        }
        producerThread.start();
        consumerThread.start();
        //producerThread.interrupt();
        //consumerThread.interrupt();
    }
}
