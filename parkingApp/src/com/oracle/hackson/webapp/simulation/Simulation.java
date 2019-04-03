package com.oracle.hackson.webapp.simulation;

import com.oracle.hackson.webapp.kafka.demo.ConsumerDemo;
import com.oracle.hackson.webapp.kafka.demo.KafkaProperties;
import com.oracle.hackson.webapp.kafka.demo.ProducerDemo;

import java.util.Timer;

public class Simulation {
    ParkPort[] portArray;
    private int num;

    public static void main(String[] args) {
        Simulation simulation_a = new Simulation(10);
        simulation_a.portArray = simulation_a.creatParkingPort(simulation_a.num);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new DoHeartBeatCheck(simulation_a.portArray[0]), 0, 10000);
        ProducerDemo producerThread = new ProducerDemo(KafkaProperties.TOPIC, true, simulation_a.portArray[0]);
        ConsumerDemo consumerThread = new ConsumerDemo(com.oracle.hackson.webapp.kafka.demo.KafkaProperties.TOPIC);
        producerThread.start();
        consumerThread.start();
    }

    public Simulation(int pnum) {
        this.num = pnum;
    }

    public ParkPort[] creatParkingPort (int pNum) {
        ParkPort[] pArray = new ParkPort[pNum];
        for (int i = 0; i < pNum; i++) {
            ParkPort p = new ParkPort(1.0,1.0,10);
            pArray[i] = p;
        }
        return pArray;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
