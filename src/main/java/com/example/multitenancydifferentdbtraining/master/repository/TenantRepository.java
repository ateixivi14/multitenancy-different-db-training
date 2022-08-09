package com.example.multitenancydifferentdbtraining.master.repository;

import com.example.multitenancydifferentdbtraining.master.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String> {
    Optional<Tenant> findByTenantId(String tenantId);
}