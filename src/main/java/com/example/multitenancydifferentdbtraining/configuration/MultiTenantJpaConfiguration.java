package com.example.multitenancydifferentdbtraining.configuration;

import lombok.RequiredArgsConstructor;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@EnableJpaRepositories(
        basePackages = { "com.example.multitenancydifferentdbtraining.common.repository" },
        entityManagerFactoryRef = "tenantEntityManagerFactory",
        transactionManagerRef = "tenantTransactionManager"
)
@EnableConfigurationProperties({JpaProperties.class})
public class MultiTenantJpaConfiguration {
  
  private final MultiTenantConnectionProvider multiTenantConnectionProvider;
  
  private final CurrentTenantIdentifierResolver currentTenantIdentifierResolver;

  @Bean(name = "tenantEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean tenantEntityManagerFactory() {
    LocalContainerEntityManagerFactoryBean localContainerEntityManager = new LocalContainerEntityManagerFactoryBean();
    localContainerEntityManager.setPackagesToScan("com.example.multitenancydifferentdbtraining.common.entity");
    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    localContainerEntityManager.setJpaVendorAdapter(vendorAdapter);
    localContainerEntityManager.setJpaPropertyMap(getHibernateProperties());
    return localContainerEntityManager;
  }

  @Bean(name = "tenantTransactionManager")
  public JpaTransactionManager tenantTransactionManager(
          @Qualifier("tenantEntityManagerFactory") EntityManagerFactory emf) {
    JpaTransactionManager tenantTransactionManager = new JpaTransactionManager();
    tenantTransactionManager.setEntityManagerFactory(emf);
    return tenantTransactionManager;
  }

  private Map<String, Object> getHibernateProperties() {
    Map<String, Object> properties = new HashMap<>();
    properties.put(org.hibernate.cfg.Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
    properties.put(org.hibernate.cfg.Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
    properties.put(org.hibernate.cfg.Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
    properties.put(org.hibernate.cfg.Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
    return properties;
  }
  
}