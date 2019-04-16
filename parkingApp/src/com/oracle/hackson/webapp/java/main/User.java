package com.oracle.hackson.webapp.java.main;

public class User {

    private Id _id = new Id();
    private String username = "";
    private String password = "";
    private int account;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccount(int account) { this.account = account; }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAccount() { return account; }

}
