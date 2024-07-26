package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long clientId;

    @Column(name = "password")
    private String password;

    @Column(name = "state")
    private String state;

    public Client(String state, String password, Person person) {
        this.state = state;
        this.password = password;
        this.person = person;
    }

    public Client() {

    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="person_Id")
    private Person person;

    @OneToMany(mappedBy = "client")
    private List<Account> accounts;


}
