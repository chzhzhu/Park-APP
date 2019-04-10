package com.oracle.hackson.webapp.simulation;

import com.google.gson.Gson;
import com.oracle.hackson.webapp.java.main.MongoDBUtils;
import com.oracle.hackson.webapp.kafka.demo.ConsumerDemo;
import com.oracle.hackson.webapp.kafka.demo.KafkaProperties;
import com.oracle.hackson.webapp.kafka.demo.ProducerDemo;

import java.util.Timer;
import java.util.TimerTask;

public class Simulation {
    public static ParkPort[] portArray;
    private int num;

    public static void main(String[] args) {

        portArray = creatParkingPort(10);
        Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                refreshEquInfo();
            }
        },0,10000);
        Timer timer2 = new Timer();
        timer2.scheduleAtFixedRate(new DoHeartBeatCheck(portArray[0]), 0, 10000);
        ProducerDemo producerThread = new ProducerDemo(KafkaProperties.TOPIC, true, portArray[0]);
        ConsumerDemo consumerThread = new ConsumerDemo(com.oracle.hackson.webapp.kafka.demo.KafkaProperties.TOPIC);
        producerThread.start();
        consumerThread.start();
    }

    public Simulation() {
        this.num = 10;
    }

    public static ParkPort[] creatParkingPort (int pNum) {
        ParkPort[] pArray = new ParkPort[pNum];
        for (int i = 0; i < pNum; i++) {
            ParkPort p = new ParkPort(1.0,1.0,10);
            pArray[i] = p;
        }
        return pArray;
    }

    public static void refreshEquInfo(){
        Gson json = new Gson();
        for (Equipment e : portArray[0].getEqu()){
            String equQuery = MongoDBUtils.findEquByEquId(KafkaProperties.DATABASE,KafkaProperties.EQUIPMENT_COL,e.getEquId());
            Equipment equipment = json.fromJson(equQuery,Equipment.class);
            e = equipment;
            System.out.println("refresh success");
        }
    }


}
