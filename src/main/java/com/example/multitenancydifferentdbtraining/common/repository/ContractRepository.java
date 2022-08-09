package com.example.multitenancydifferentdbtraining.common.repository;

import com.example.multitenancydifferentdbtraining.common.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    Contract findContractById(Long id);
    
    Contract findContractByClientId(Long id);
    
    List<Contract> findAll();

    @Modifying
    @Transactional
    void deleteContractById(Long id);
}
