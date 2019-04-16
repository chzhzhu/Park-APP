package com.oracle.hackson.webapp.java.main;

import java.util.Date;

public class ParkSpaInfo {
    private Id _id = new Id();
    private String username;
    private int parkPortId;
    private int equId;
    private Date startTime;
    private Date endTime;
    private int account;
    private int price;

    public String getUsername() {
        return username;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getAccount() {
        return account;
    }

    public int getParkPortId() {
        return parkPortId;
    }

    public int getEquId() {
        return equId;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setEquId(int equId) {
        this.equId = equId;
    }

    public void setParkPortId(int parkPortId) {
        this.parkPortId = parkPortId;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getParkPrice(Date startTime, Date endTime) {
        long diff = startTime.getTime() - endTime.getTime();
        int hours = (int)diff / (1000 * 60 * 60 );
        int price = hours * 2;
        return price;
    }
}
