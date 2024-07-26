package com.rodrigo.batch_processing.config.sanetizeDirectory;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SanetizeDirectoryStep {

  private final StepBuilderFactory stepBuilderFactory;

  @Bean("SanetizeDirectoryStep")
  public Step sanetizeDirectoryStep(SanetizeDirectoryTask task) {
    return stepBuilderFactory.get("sanetizeDirectoryStep")
        .tasklet(task)
        .build();
  }

}
