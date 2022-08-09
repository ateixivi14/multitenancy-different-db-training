package com.example.multitenancydifferentdbtraining.dto;

import com.example.multitenancydifferentdbtraining.common.entity.Client;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class ClientDto {
    private final Long id;
    private final String name;

    public static ClientDto toDto (Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .build();

    }
}
