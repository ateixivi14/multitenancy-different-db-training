package com.example.multitenancydifferentdbtraining.service;

import com.example.multitenancydifferentdbtraining.dto.ContractDto;
import com.example.multitenancydifferentdbtraining.common.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;

    public List<ContractDto> getContracts() {
        List<ContractDto> contractDtoList = new ArrayList<>();
        contractRepository.findAll().forEach(client -> contractDtoList.add(ContractDto.toDto(client)));
        return contractDtoList;
    }

    public ContractDto getContractById(Long contractId) {
        return ContractDto.toDto(contractRepository.findContractById(contractId));
    }

    public ContractDto getContractByClientId(Long clientId) {
        return ContractDto.toDto(contractRepository.findContractByClientId(clientId));
    }

}
