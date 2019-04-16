package com.oracle.hackson.webapp.java.main;

import java.util.Date;

public class OrderInfo {
    private String username;
    private int parkPortId;
    private int equId;
    private Date startTime;
    private Date endTime;
    private int price;
    private int orderId;
    private boolean payFlag = false;


    public String getUsername() {
        return username;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getParkPortId() {
        return parkPortId;
    }

    public int getEquId() {
        return equId;
    }

    public int getPrice() {
        return price;
    }

    public int getOrderId() {
        return orderId;
    }

    public boolean isPayFlag() {
        return payFlag;
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

    public void setPrice() {
        this.price = getParkPrice(startTime, endTime);
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setPayFlag(boolean payFlag) {
        this.payFlag = payFlag;
    }

    public int getParkPrice(Date startTime, Date endTime) {
        long diff = endTime.getTime() - startTime.getTime();
        int hours = (int)(diff / (1000 * 60 ));
        int price = hours * 1;
        return price;
    }
}
