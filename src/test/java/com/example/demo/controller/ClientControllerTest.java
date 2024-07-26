package com.example.demo.controller;


import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.POJO.ClientPOJO;
import com.example.demo.service.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClientService clientService;

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private PersonRepository personRepository;


       @Test
        public void testGetAllClients() throws Exception {
            List<ClientPOJO> clients = List.of(
                    new ClientPOJO(0L, "Giovanny", "Male", 27, "1725051369",
                            "Budapesti ut 7", "212121"));
                    clients.get(0).setClientId(0L);
                    clients.get(0).setState("true");
                    clients.get(0).setPassword("123456");

            Mockito.when(clientService.findAll()).thenReturn(clients);
            mockMvc.perform(get("/clients")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(clients)));
        }

   @Test
    public void testGetClientById() throws Exception {
        ClientPOJO client = new ClientPOJO(0L, "Giovanny", "Male", 27, "1725051369",
                "Budapesti ut 7", "212121");
        client.setClientId(0L);
        client.setState("true");
        client.setPassword("123456");

        Mockito.when(clientService.findById(0L)).thenReturn(client);
        mockMvc.perform(get("/clients/0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(client)));
    }

    @Test
    public void testDeleteTutorial() throws Exception {
        long id = 1L;
        Mockito.doNothing().when(clientRepository).deleteById(id);
        mockMvc.perform(delete("/clients/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
