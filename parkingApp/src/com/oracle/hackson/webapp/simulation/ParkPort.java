package com.oracle.hackson.webapp.simulation;



public class ParkPort extends Equipment {

    double longitude;
    double latitude;
    int parkPortId;
    int equNum;
    int unlockedEquId;
    int lockedEquId;
    int disabledEquId;
    public Equipment[] equ;
    private static int id = 1;


    public ParkPort(double lon, double lat, int num) {
        resetId();
        resetNum();
        this.setParkPortId(id);
        this.setLongitude(lon);
        this.setLatitude(lat);
        this.setEquNum(num);
        equ = new Equipment[num];
        for (int i = 0; i <num ; i++) {
            equ[i] =new Equipment(id);
        }
        id++;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    private void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    private void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getEquNum() {
        return equNum;
    }

    private void setEquNum(int equNum) {
        this.equNum = equNum;
    }

    public int getDisabledEquId() {
        return disabledEquId;
    }

    public int getLockedEquId() {
        return lockedEquId;
    }

    public int getUnlockedEquId() {
        return unlockedEquId;
    }

    public int getParkPortId() {
        return parkPortId;
    }

    private void setParkPortId(int parkPortId) {
        this.parkPortId = parkPortId;
    }

    private void resetNum() {
        this.num = 1;
    }

    public void resetId() {
        this.id = 1;
    }
}
