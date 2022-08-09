package com.example.multitenancydifferentdbtraining.master.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tenant {

    @Id
    @Column(name = "tenant_id")
    private String tenantId;
    
    private String username;
    
    private String db;
    
    private String password;
    
    private String url;

}