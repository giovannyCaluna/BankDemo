package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountId;

    @Column(name = "accountNumber")
    private String accountNumber;

    @Column(name = "type")
    private String type;

    @Column(name = "initialAmount")
    private Double initialAmount;

    @Column(name = "state")
    private String state;

    public Account(String accountNumber, String type, Double initialAmount, String state, Client client) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.initialAmount = initialAmount;
        this.state = state;
        this.client = client;
    }

    public Account() {

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

    public Double getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(Double initialAmount) {
        this.initialAmount = initialAmount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public long getAccountId() {
        return accountId;
    }
    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="client_Id")
    private Client client;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

}
