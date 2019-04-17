package com.oracle.hackson.webapp.simulation;

import com.google.gson.Gson;
import com.oracle.hackson.webapp.java.main.MongoDBUtils;
import com.oracle.hackson.webapp.kafka.demo.ConsumerDemo;
import com.oracle.hackson.webapp.kafka.demo.KafkaProperties;
import com.oracle.hackson.webapp.kafka.demo.ProducerDemo;

import java.util.Timer;


public class Simulation {
    public static ParkPort[] portArray;
    private int num;
    public static int id = 1;

    public static void main(String[] args) {
        Double[] points = {118.796, 32.024, 118.864, 32.033, 118.899, 31.900, 119.057, 32.027, 118.588, 32.017, 118.694, 31.799,119.127, 31.795, 118.878, 31.736, 119.116, 31.903, 118.416, 31.918};
            portArray = creatParkingPort(points,10);
        for (int i = 0; i < 10; i++) {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new DoHeartBeatCheck(portArray[i]), 0, 10000);
            ProducerDemo producerThread = new ProducerDemo(KafkaProperties.TOPICS[i], true, portArray[i]);
            ConsumerDemo consumerThread = new ConsumerDemo(com.oracle.hackson.webapp.kafka.demo.KafkaProperties.TOPICS[i]);
            producerThread.start();
            consumerThread.start();
        }
    }

    public Simulation() {
        this.num = 10;
    }

    public static ParkPort[] creatParkingPort (Double[] points, int pNum) {
        ParkPort[] pArray = new ParkPort[pNum];
        for (int i = 0; i < pNum*2; i=i+2) {
            ParkPort p = new ParkPort(points[i],points[i+1],10);
            pArray[i/2] = p;
        }
        return pArray;
    }


}
