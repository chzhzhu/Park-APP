package com.oracle.hackson.webapp.simulation;

import java.util.Timer;

public class Simulation {
    ParkPort[] portArray;
    private int num;

    public static void main(String[] args) {
        Simulation simulation_a = new Simulation(10);
        simulation_a.portArray = simulation_a.creatParkingPort(simulation_a.num);
        Timer timer = new Timer();
        timer.schedule(new DoHeartBeatCheck(simulation_a.portArray[0]), 1000, 3000);

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
