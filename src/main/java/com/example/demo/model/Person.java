package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long personId;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "identification")
    private String identification;
    @Column(name = "address")
    private String address;
    @Column(name = "telefono")
    private String telefono;

    public Person(String name, String gender, Integer age, String identification, String address, String telefono) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.identification = identification;
        this.address = address;
        this.telefono = telefono;
    }
    public Person() {

    }

    public long getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getIdentification() {
        return identification;
    }

    public String getAddress() {
        return address;
    }

    public String getTelefono() {
        return telefono;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @OneToOne(mappedBy = "person")
    private Client client;



}
