package com.oracle.hackson.webapp.simulation;

import java.util.TimerTask;


public class DoHeartBeatCheck extends TimerTask{
    public int TIMEOUT_COUNT = 5;
    ParkPort parkPort;

    public DoHeartBeatCheck(ParkPort p) {
        this.parkPort = p;
    }
    public void run() {

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

    }
}
