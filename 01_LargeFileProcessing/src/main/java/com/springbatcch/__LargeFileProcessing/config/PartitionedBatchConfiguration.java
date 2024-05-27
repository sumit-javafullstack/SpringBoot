package com.springbatcch.__LargeFileProcessing.config;

import com.springbatcch.__LargeFileProcessing.batch.CustomerProcessor;
import com.springbatcch.__LargeFileProcessing.batch.CustomerReader;
import com.springbatcch.__LargeFileProcessing.batch.CustomerWriter;
import com.springbatcch.__LargeFileProcessing.model.Customer;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.util.List;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class PartitionedBatchConfiguration {

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Autowired private Partitioner partitioner;

  @Autowired private Step workerStep;

  @Bean
  public PartitionHandler partitionHandler() {
    TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler();
    handler.setStep(workerStep);
    handler.setTaskExecutor(new SimpleAsyncTaskExecutor());
    handler.setGridSize(10); // Grid size
    return handler;
  }

  @Bean
  public Step masterStep() {
    return stepBuilderFactory
        .get("masterStep")
        .partitioner(workerStep.getName(), partitioner) // Configure the partitioner
        .partitionHandler(partitionHandler()) // Set the partition handler
        .build();
  }

  @Bean
  public Step workerStep(
      CustomerReader reader, CustomerProcessor processor, CustomerWriter writer) {
    return stepBuilderFactory
        .get("workerStep")
        .<Map<String, List<Customer>>, List<Customer>>chunk(1000)
        .reader(customerReader())
        .processor(customerProcessor())
        .writer(customerWriter())
        .build();
  }

      @Bean
      public  CustomerReader customerReader(){
          return new CustomerReader();
      }
  @Bean
  public  CustomerProcessor customerProcessor(){
    return new CustomerProcessor();
  }
  @Bean
  public  CustomerWriter customerWriter(){
    return new CustomerWriter();
  }
}
