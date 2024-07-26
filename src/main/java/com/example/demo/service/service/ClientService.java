package com.example.demo.service.service;

import com.example.demo.model.Client;
import com.example.demo.model.Person;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.POJO.ClientPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PersonRepository personRepository;

    public List<ClientPOJO> findAll() {
        List<Client> clients = clientRepository.findAll();
        List<ClientPOJO> clientPOJOs = new ArrayList<>();
        for (Client client : clients) {
            ClientPOJO clientPOJO = new ClientPOJO();
            clientPOJO.setPersonId(client.getPerson().getPersonId());
            clientPOJO.setName(client.getPerson().getName());
            clientPOJO.setGender(client.getPerson().getGender());
            clientPOJO.setAge(client.getPerson().getAge());
            clientPOJO.setClientId(client.getClientId());
            clientPOJO.setIdentification(client.getPerson().getIdentification());
            clientPOJO.setAddress(client.getPerson().getAddress());
            clientPOJO.setTelefono(client.getPerson().getTelefono());
            clientPOJO.setPassword(client.getPassword());
            clientPOJO.setState(client.getState());
            clientPOJOs.add(clientPOJO);
        }
        return clientPOJOs;
    }

    public ClientPOJO findById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        ClientPOJO clientPOJO = new ClientPOJO();

        if (client.isPresent()) {
            clientPOJO.setClientId(client.get().getClientId());
            clientPOJO.setAddress(client.get().getPerson().getAddress());
            clientPOJO.setTelefono(client.get().getPerson().getTelefono());
            clientPOJO.setPassword(client.get().getPassword());
            clientPOJO.setState(client.get().getState());
            return clientPOJO;
        } else {
            return clientPOJO;
        }


    }
}