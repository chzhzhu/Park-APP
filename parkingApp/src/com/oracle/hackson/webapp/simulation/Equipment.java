package com.oracle.hackson.webapp.simulation;

import java.util.Timer;
import java.util.TimerTask;

public class Equipment {
    int equId;
    boolean alive;
    String equStatus;
    String equName;
    public Ping ping= new Ping();
    public static int num = 1;

    public Equipment() {

    }

    public Equipment (int parkId) {
        this.createEqu(parkId);
        this.heartBeat();
    }

    public void createEqu(int parkId) {
        int id = createEquId(parkId);
        this.setEquId(id);
        this.setEquName("PSNo."+ num);
        this.setAlive(true);
        this.setEquStatus("Locked");
    }
    public long getEquId() {
        return equId;
    }

    public String getEquStatus() {
        return equStatus;
    }

    private void setEquId(int equId) {
        this.equId = equId;
    }

    public void setEquStatus(String equStatus) {
        if (isUpdatable()) {
            this.equStatus = equStatus;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isUpdatable() {
        boolean updateFlag = true;
        if (!this.isAlive()) {
            updateFlag = false;
            this.setEquStatus("Locked");
        }
        return updateFlag;
    }

    private int createEquId (int parkId) {
        int id = parkId * 1000 + num;
        num++;
        return id;
    }

    private void heartBeat() {
        if (equId == 1006) {
            return;
        }
        Timer timer = new Timer();
        class myTimerTask extends TimerTask {
            @Override
            public void run() {
                ping.refresh();
            }
        };
        long delay = 0L;
        long period = 10000L;
        timer.scheduleAtFixedRate(new myTimerTask(), delay, period);
    }

    public String getEquName() {
        return equName;
    }

    private void setEquName(String equName) {
        this.equName = equName;
    }
}
