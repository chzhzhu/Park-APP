package com.oracle.hackson.webapp.java.main;



public class User {

    private ID _id = new ID();
    private String username = "";
    private String password = "";


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    class ID{
        private String id = "";
    }
}
