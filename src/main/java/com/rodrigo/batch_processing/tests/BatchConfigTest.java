package com.rodrigo.batch_processing.tests;

import com.rodrigo.batch_processing.domain.CardType;
import com.rodrigo.batch_processing.tests.firstStep.FirstStepProcessor;
import com.rodrigo.batch_processing.tests.firstStep.FirstStepProcessorListener;
import com.rodrigo.batch_processing.tests.firstStep.FirstStepReader;
import com.rodrigo.batch_processing.tests.firstStep.FirstStepWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;

// @Configuration
// @EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfigTest {

  private final FirstStepReader firstStepReader;
  private final FirstStepProcessor firstStepProcessor;
  private final FirstStepProcessorListener firstStepProcessorListener;
  private final FirstStepWriter firstStepWriter;

  @Bean
  public Job myJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
    return jobBuilderFactory.get("myTestJob")
        .start(firstStep(stepBuilderFactory))
        // .next(secondStep(stepBuilderFactory))
        .build();
  }

  @Bean
  public Step firstStep(StepBuilderFactory stepBuilderFactory) {
    return stepBuilderFactory.get("firstStepTest")
        .<CardType, CardType>chunk(1)
        .reader(firstStepReader.read())
        .processor(firstStepProcessor)
        .writer(firstStepWriter)
        .listener(firstStepProcessorListener)
        .build();
  }

}
