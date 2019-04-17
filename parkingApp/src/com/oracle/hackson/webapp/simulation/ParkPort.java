package com.oracle.hackson.webapp.simulation;



public class ParkPort {

    double longitude;
    double latitude;
    int parkPortId;
    int equNum;
    int unlockedEquNum;
    int lockedEquNum;
    int disabledEquNum;
    public Equipment[] equ;



    public ParkPort(double lon, double lat, int num) {
        //resetId();
        this.setParkPortId(Simulation.id);
        this.setLongitude(lon);
        this.setLatitude(lat);
        this.setEquNum(num);
        Equipment.num = 1;
        equ = new Equipment[num];
        for (int i = 0; i <num ; i++) {
            equ[i] =new Equipment(Simulation.id);
        }
        Simulation.id++;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getEquNum() {
        return equNum;
    }

    private void setEquNum(int equNum) {
        this.equNum = equNum;
    }

    public void setUnlockedEquNum() {
        Equipment equipment = null;
        int count = 0;
        for(Equipment e : this.getEqu()){
            equipment = e;
            if (equipment.getEquStatus().equals("Unlocked")) {
                count++;
            }
        }
        unlockedEquNum = count;
    }

    public void setDisabledEquNum() {
        Equipment equipment = null;
        int count = 0;
        for(Equipment e : this.getEqu()){
            equipment = e;
            if (!equipment.isAlive()) {
                count++;
            }
        }
        disabledEquNum = count;
    }

    public void setLockedEquNum() {
        Equipment equipment = null;
        int count = 0;
        for(Equipment e : this.getEqu()){
            equipment = e;
            if (equipment.getEquStatus().equals("Locked")) {
                count++;
            }
        }
        lockedEquNum = count;
    }

    public int getDisabledEquNum() {
        return disabledEquNum;
    }

    public int getLockedEquNum() {

        return lockedEquNum;
    }

    public int getUnlockedEquNum() {
        return unlockedEquNum;
    }

    public int getParkPortId() {
        return parkPortId;
    }

    private void setParkPortId(int parkPortId) {
        this.parkPortId = parkPortId;
    }

    public void resetId() {
        Simulation.id = 1;
    }

    public Equipment[] getEqu() {
        return equ;
    }

    public Equipment getEquByEquId(int equId){
        Equipment equipment = null;
        for(Equipment e : this.getEqu()){
            if (e.getEquId() == equId)
                equipment = e;
        }
        return equipment;
    }

    public Equipment getEquByEquName(String equName){
        Equipment equipment = null;
        for(Equipment e : this.getEqu()){
            if (e.getEquName() == equName)
                equipment = e;
        }
        return equipment;
    }
}
