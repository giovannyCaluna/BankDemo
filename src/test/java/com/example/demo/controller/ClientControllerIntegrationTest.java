//package com.example.demo.controller;
//
//import com.example.demo.model.Client;
//import com.example.demo.model.Person;
//import com.example.demo.repository.AccountRepository;
//import com.example.demo.repository.ClientRepository;
//import com.example.demo.repository.TransactionRepository;
//import com.example.demo.service.POJO.ClientPOJO;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
////@SpringBootTest
//@AutoConfigureMockMvc
//public class ClientControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private ClientRepository clientRepository;
//    @Autowired
//    private TransactionRepository transactionRepository;
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @BeforeEach
//    public void setup() {
//        transactionRepository.deleteAll();
//        accountRepository.deleteAll();
//        clientRepository.deleteAll();
//    }
//
//   //@Test
//    public void testDeleteTutorial() throws Exception {
//        Person person = new Person("Giovanny", "Male", 27,"1725051369", "Budapesti ut 7","212121" );
//        Client client = new Client("true", "123456", person);
//        // Save a new client
//        client = clientRepository.save(client);
//        // Perform the delete operation
//        mockMvc.perform(delete("/clients/{id}", client.getClientId())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
//}
