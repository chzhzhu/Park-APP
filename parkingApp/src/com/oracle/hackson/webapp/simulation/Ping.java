package com.oracle.hackson.webapp.simulation;

public class Ping {
    int count = 0;
    volatile boolean reset = false;

    public void refresh() {
        if (reset == false)
            reset = true;
    }

}
