package com.example.multitenancydifferentdbtraining.service;

import com.example.multitenancydifferentdbtraining.controller.ClientRequest;
import com.example.multitenancydifferentdbtraining.dto.ClientDto;
import com.example.multitenancydifferentdbtraining.common.entity.Client;
import com.example.multitenancydifferentdbtraining.common.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    
    public List<ClientDto> getClients() {
        List<ClientDto> clientDtoList = new ArrayList<>();
        clientRepository.findAll().forEach(client -> clientDtoList.add(ClientDto.toDto(client)));
        return clientDtoList;
    }
    
    public ClientDto getClientById(Long id) {
        return ClientDto.toDto(clientRepository.findClientById(id));
    }

    public void removeClientById(Long id) {
       clientRepository.deleteClientById(id);
    }
    
    public void addClient(ClientRequest clientRequest) {
        Client client = Client.builder()
                .name(clientRequest.getName())
                .deleted(clientRequest.isDeleted())
                .nationalId(clientRequest.getNationalId())
                .build();
        clientRepository.save(client);
    }

}
