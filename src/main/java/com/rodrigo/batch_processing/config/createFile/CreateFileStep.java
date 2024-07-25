package com.rodrigo.batch_processing.config.createFile;

import com.rodrigo.batch_processing.domain.CardType;
import com.rodrigo.batch_processing.domain.FileControl;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CreateFileStep {

  private final StepBuilderFactory stepBuilderFactory;

  @Bean("CreateFileStep")
  public Step createFileStep(
      @Qualifier("CardTypeReader") JdbcCursorItemReader<CardType> cardTypeReader,
      CardTypeProcessor cardTypeProcessor,
      @Qualifier("FileControlWriter") JdbcBatchItemWriter<FileControl> fileControlWriter) {
    return stepBuilderFactory
        .get("createFileStep")
        .allowStartIfComplete(true)
        .<CardType, FileControl>chunk(1)
        .reader(cardTypeReader)
        .processor(cardTypeProcessor)
        .writer(fileControlWriter)
        .build();
  }

}
