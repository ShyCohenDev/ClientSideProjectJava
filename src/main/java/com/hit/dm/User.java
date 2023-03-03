package com.hit.dm;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    private final String id;
    private String userName;
    private String hashedPassword;
    private float balance;

    public User(String userName, String hashedPassword) {
        this.id = UUID.randomUUID().toString();
        this.userName = userName;
        this.hashedPassword = hashedPassword;
        this.balance = 0F;
    }

    public User(String userName, String hashedPassword, float balance) {
        this.id = UUID.randomUUID().toString();
        this.userName = userName;
        this.hashedPassword = hashedPassword;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "<" + this.userName + ", " + this.id + ">";
    }
}
