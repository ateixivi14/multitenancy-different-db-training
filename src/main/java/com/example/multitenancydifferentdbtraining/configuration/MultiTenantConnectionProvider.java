package com.example.multitenancydifferentdbtraining.configuration;

import com.example.multitenancydifferentdbtraining.master.entity.Tenant;
import com.example.multitenancydifferentdbtraining.master.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MultiTenantConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
    
    private final DataSource masterDataSource;
    
    private final TenantRepository tenantRepository;

    private Map<String, DataSource> dataSources = new LinkedHashMap<>();
    
    @Override
    protected DataSource selectAnyDataSource() {
        return masterDataSource;
    }

    @Override
    protected DataSource selectDataSource(String tenantId) {
        if (dataSources.get(tenantId) != null){
            return dataSources.get(tenantId);
        }
        Tenant tenant = tenantRepository.findByTenantId(tenantId)
                .orElseThrow(() -> new RuntimeException("No such tenant: " + tenantId));
        
        return createAndConfigureDataSource(tenant);
    }
    
    private DataSource createAndConfigureDataSource(Tenant tenant) {
        DataSource dataSource =  DataSourceBuilder
            .create()
            .driverClassName(DRIVER_CLASS_NAME)
            .username(tenant.getUsername())
            .password(tenant.getPassword())
            .url(tenant.getUrl())
                .build();
        dataSources.put(tenant.getTenantId(), dataSource);
        return dataSource;
    }

}