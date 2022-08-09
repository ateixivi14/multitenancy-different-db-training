package com.example.multitenancydifferentdbtraining.dto;

import com.example.multitenancydifferentdbtraining.common.entity.Contract;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class ContractDto {
    private final Long id;
    private final String reference;
    private final int credit;

    public static ContractDto toDto(Contract contract) {
        return ContractDto.builder()
                .id(contract.getId())
                .credit(contract.getCredit())
                .reference(contract.getReference())
                .build();
    }
}
