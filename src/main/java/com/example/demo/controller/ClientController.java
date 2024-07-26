package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.model.Person;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.POJO.ClientPOJO;
import com.example.demo.service.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ClientController {
    @Autowired
    ClientService clientService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/clients")
    public ResponseEntity<List<ClientPOJO>> getAllTutorials(@RequestParam(required = false) String title) {
        try {
            List<ClientPOJO> clients =  clientService.findAll();
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientPOJO> getTutorialById(@PathVariable("id") long id) {
        ClientPOJO client =  clientService.findById(id);
            return new ResponseEntity<>(client, HttpStatus.OK);

    }

    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        try {
            Person person = new Person(
                    client.getPerson().getName(),
                    client.getPerson().getGender(),
                    client.getPerson().getAge(),
                    client.getPerson().getIdentification(),
                    client.getPerson().getAddress(),
                    client.getPerson().getTelefono()
            );
            person = this.personRepository.save(person);
            Client clientNew = new Client(
                    client.getState(), client.getPassword(), person
            );

           clientNew = this.clientRepository.save(clientNew);

            return new ResponseEntity<>(clientNew, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") long id, @RequestBody Client client) {
        Optional<Client> clientData = clientRepository.findById(id);
        if (clientData.isPresent()) {
            Client mClient = clientData.get();
            mClient.getPerson().setName(client.getPerson().getName());
            mClient.getPerson().setGender(client.getPerson().getGender());
            mClient.getPerson().setAge(client.getPerson().getAge());
            mClient.getPerson().setIdentification(client.getPerson().getIdentification());
            mClient.getPerson().setAddress(client.getPerson().getAddress());
            mClient.getPerson().setTelefono(client.getPerson().getTelefono());
            mClient.setPassword(client.getPassword());
            mClient.setState(client.getState());
            mClient.setPerson(client.getPerson());
          personRepository.save(mClient.getPerson());
            return new ResponseEntity<>(clientRepository.save(mClient), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable("id") long id) {
        try {
            clientRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

