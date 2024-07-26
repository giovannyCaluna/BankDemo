package com.example.demo.service.POJO;


public class ClientPOJO extends PersonPOJO {
    private long clientId;
    private String password;
    private String state;

    public ClientPOJO() {
    }

    public ClientPOJO(long personId, String name, String gender,
                      Integer age, String identification,
                      String address, String telefono) {
        super(personId, name, gender, age, identification, address, telefono);
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
