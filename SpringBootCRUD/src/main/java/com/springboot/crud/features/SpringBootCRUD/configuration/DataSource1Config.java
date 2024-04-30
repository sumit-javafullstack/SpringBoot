package com.springboot.crud.features.SpringBootCRUD.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = {"com.springboot.crud.features.SpringBootCRUD.repository.employee"},
    entityManagerFactoryRef = "entityManagerFactory1",
    transactionManagerRef = "transactionManager1")
public class DataSource1Config {

  @Bean(name = "datasource1")
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource.datasource1")
  public DataSource getStudDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Primary
  @Bean(name = "entityManagerFactory1")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory1(
      EntityManagerFactoryBuilder builder, @Qualifier("datasource1") DataSource dataSource) {
    return builder
        .dataSource(dataSource)
        .packages("com.springboot.crud.features.SpringBootCRUD.model.employee")
        .persistenceUnit("datasource1")
        .build();
  }

  @Primary
  @Bean(name = "transactionManager1")
  public PlatformTransactionManager transactionManager1(
      @Qualifier("entityManagerFactory1") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
