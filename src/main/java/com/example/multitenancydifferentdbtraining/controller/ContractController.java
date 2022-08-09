package com.example.multitenancydifferentdbtraining.controller;

import com.example.multitenancydifferentdbtraining.dto.ContractDto;
import com.example.multitenancydifferentdbtraining.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contracts")
public class ContractController {

   private final ContractService contractService;

    @GetMapping("/list")
    public List<ContractDto> getContracts() {
        return contractService.getContracts();
    }

}
