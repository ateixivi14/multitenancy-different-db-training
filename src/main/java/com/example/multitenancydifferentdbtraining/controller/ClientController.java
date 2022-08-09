package com.example.multitenancydifferentdbtraining.controller;

import com.example.multitenancydifferentdbtraining.dto.ClientDto;
import com.example.multitenancydifferentdbtraining.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/list")
    public List<ClientDto> getClients() {
        return clientService.getClients();
    }

   @PutMapping("/{clientId}/remove")
    public void removeClient(@PathVariable Long clientId) {
        clientService.removeClientById(clientId);
    }
    
    @PostMapping("/add-client")
    public void addClient(ClientRequest clientRequest) {
        clientService.addClient(clientRequest);
    }

}
