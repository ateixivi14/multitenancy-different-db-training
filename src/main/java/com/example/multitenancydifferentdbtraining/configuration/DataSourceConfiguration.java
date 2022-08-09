package com.example.multitenancydifferentdbtraining.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@EnableJpaRepositories(
        basePackages = { "com.example.multitenancydifferentdbtraining.master.repository" },
        entityManagerFactoryRef = "masterEntityManagerFactory",
        transactionManagerRef = "masterTransactionManager"
) 
public class DataSourceConfiguration {
  
  private final DataSourceProperties dataSourceProperties;

  @Bean(name = "masterDataSource")
  @Primary
  public DataSource masterDataSource() {
    return DataSourceBuilder
        .create()
        .driverClassName(dataSourceProperties.determineDriverClassName())
        .username(dataSourceProperties.getUsername())
        .password(dataSourceProperties.getPassword())
        .url(dataSourceProperties.getUrl())
        .build();
  }
  

  @Bean(name = "masterEntityManagerFactory")
  @Primary
  public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory() {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(masterDataSource());
    em.setPackagesToScan("com.example.multitenancydifferentdbtraining.master.entity");
    em.setJpaVendorAdapter(vendorAdapter);
    return em;
  }

  @Bean(name = "masterTransactionManager")
  @Primary
  public JpaTransactionManager masterTransactionManager(
          @Qualifier("masterEntityManagerFactory") EntityManagerFactory emf) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(emf);
    return transactionManager;
  }
  
}