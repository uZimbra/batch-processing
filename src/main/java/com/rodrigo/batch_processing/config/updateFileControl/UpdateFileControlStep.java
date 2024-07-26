package com.rodrigo.batch_processing.config.updateFileControl;

import com.rodrigo.batch_processing.domain.FileControl;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateFileControlStep {

  private final StepBuilderFactory stepBuilderFactory;

  @Bean("UpdateFileControlStep")
  public Step updateFileControlStep(
      @Qualifier("FileControlReader") JdbcCursorItemReader<FileControl> reader,
      UpdateFileControlProcessor processor,
      @Qualifier("UpdateFileControlWriter") JdbcBatchItemWriter<FileControl> writer) {
    return stepBuilderFactory.get("writeFileStep")
        .allowStartIfComplete(true)
        .<FileControl, FileControl>chunk(1)
        .reader(reader)
        .processor(processor)
        .writer(writer)
        .build();
  }

}
