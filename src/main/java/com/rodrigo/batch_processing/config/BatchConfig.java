package com.rodrigo.batch_processing.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

  private final JobBuilderFactory jobBuilderFactory;

  @Bean
  public Job job(
      @Qualifier("SanetizeDirectoryStep") Step sanetizeDirectoryStep,
      @Qualifier("CreateFileStep") Step createFileStep,
      @Qualifier("WriteFileStep") Step writeFileStep,
      @Qualifier("UpdateFileControlStep") Step updateFileControlStep) {
    return jobBuilderFactory
        .get("job")
        .incrementer(new RunIdIncrementer())
        .start(sanetizeDirectoryStep)
        .next(createFileStep)
        .next(writeFileStep)
        .next(updateFileControlStep)
        .build();
  }

}
