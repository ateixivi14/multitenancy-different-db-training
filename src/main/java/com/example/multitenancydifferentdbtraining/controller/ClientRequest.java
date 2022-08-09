package com.example.multitenancydifferentdbtraining.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {
    private Long id;
    private String name;
    private boolean deleted;
    private String nationalId;
}
