package com.oracle.hackson.webapp.simulation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Equipment {
    long equId;
    boolean alive;
    String equStatus;
    public Ping ping= new Ping();
    public static int num = 1;
    public static int unique = 1;

    public Equipment() {

    }

    public Equipment (int parkId) {
        this.createEqu(parkId);
        this.heartBeat();
    }

    public void createEqu(int parkId) {
        long id = createEquId(parkId);
        this.setEquId(id);
        this.setAlive(true);
        this.setEquStatus("Locked");
    }
    public long getEquId() {
        return equId;
    }

    public String getEquStatus() {
        return equStatus;
    }

    private void setEquId(long equId) {
        this.equId = equId;
    }

    private void setEquStatus(String equStatus) {
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

    private long createEquId (long parkId) {
        Date dNow = new Date( );
        SimpleDateFormat ft =
                new SimpleDateFormat ("yyyyMMdd");
        long date = Long.parseLong(ft.format(dNow));
        long id = parkId * 100000000000L + date * 1000L  + num;
        num++;
        unique++;
        return id;
    }

    private void heartBeat() {
        if (equId == 120190328006L) {
            return;
        }
        Timer timer = new Timer();
        class myTimerTask extends TimerTask {
            @Override
            public void run() {
                ping.refresh();
            }
        };
        long delay = 1000L;
        long period = 3000L;
        timer.schedule(new myTimerTask(), delay, period);
    }
}
