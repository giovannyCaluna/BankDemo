package com.example.demo.service.exception;

public class InsuficientBalance extends RuntimeException{
    private String message;
    public InsuficientBalance() {}

    public InsuficientBalance(String msg) {
        super(msg);
        this.message = msg;
    }
}
