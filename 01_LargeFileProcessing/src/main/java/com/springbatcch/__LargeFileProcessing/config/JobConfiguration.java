package com.springbatcch.__LargeFileProcessing.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {

    @Autowired(required = false)
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private Step masterStep;

    @Bean
    public Job partitionedJob() {
        return jobBuilderFactory.get("partitionedJob")
                .incrementer(new RunIdIncrementer())
                .start(masterStep) // Execute the master step with partitions
                .build();
    }
}
