package com.springboot.crud.features.SpringBootCRUD.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = {"com.springboot.crud.features.SpringBootCRUD.repository.student"},
    entityManagerFactoryRef = "entityManagerFactory2",
    transactionManagerRef = "transactionManager2")
public class DataSource2Config {

  @Bean(name = "datasource2")
  @ConfigurationProperties(prefix = "spring.datasource.datasource2")
  public DataSource getEmpDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "entityManagerFactory2")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory2(
      EntityManagerFactoryBuilder builder, @Qualifier("datasource2") DataSource dataSource) {
    return builder
        .dataSource(dataSource)
        .packages("com.springboot.crud.features.SpringBootCRUD.model.student")
        .persistenceUnit("datasource2")
        .build();
  }

  @Bean(name = "transactionManager2")
  public PlatformTransactionManager transactionManager2(
      @Qualifier("entityManagerFactory2") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
