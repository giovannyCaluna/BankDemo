package com.example.demo.service.POJO;

import java.util.Date;

public class ReportLine {
    private Date date;
    private String client;
    private String accountNumber;
    private String type;
    private Double initialBalance;
    private String state;
    private String movement;
    private Double finalBalance;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public Double getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(Double finalBalance) {
        this.finalBalance = finalBalance;
    }

    public ReportLine(Date date, String client, String accountNumber, String type, Double initialBalance, String state, String movement, Double finalBalance) {
        this.date = date;
        this.client = client;
        this.accountNumber = accountNumber;
        this.type = type;
        this.initialBalance = initialBalance;
        this.state = state;
        this.movement = movement;
        this.finalBalance = finalBalance;
    }
}
