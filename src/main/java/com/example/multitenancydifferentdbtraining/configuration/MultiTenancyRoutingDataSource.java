package com.example.multitenancydifferentdbtraining.configuration;

import com.example.multitenancydifferentdbtraining.tenant.TenantIdentifierResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MultiTenancyRoutingDataSource extends AbstractRoutingDataSource {
    
    private final MultiTenantConnectionProvider multiTenantConnectionProvider;
    
    private final TenantIdentifierResolver identifierResolver;

    Map<Object, Object> targetDataSources = new HashMap<>();
    
    
    @PostConstruct
    public void setUp() {
        String tenantId = identifierResolver.resolveCurrentTenantIdentifier();
        DataSource datasource = multiTenantConnectionProvider.selectDataSource(tenantId);
        targetDataSources.put(tenantId, datasource);
        setTargetDataSources(targetDataSources);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return identifierResolver.resolveCurrentTenantIdentifier();
    }
    
}
