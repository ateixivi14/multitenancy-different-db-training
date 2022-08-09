package com.example.multitenancydifferentdbtraining.common.repository;

import com.example.multitenancydifferentdbtraining.common.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findAll();
    
    Client findClientById(Long id);

    @Modifying
    @Transactional
    void deleteClientById(Long id);

}
