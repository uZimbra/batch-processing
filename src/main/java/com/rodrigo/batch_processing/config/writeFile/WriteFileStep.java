package com.rodrigo.batch_processing.config.writeFile;

import com.rodrigo.batch_processing.config.ExceptionSkipPolicy;
import com.rodrigo.batch_processing.domain.Invoice;
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
public class WriteFileStep {

  private final StepBuilderFactory stepBuilderFactory;

  @Bean("WriteFileStep")
  public Step writeFileStep(
      @Qualifier("InvoiceReader") JdbcCursorItemReader<Invoice> reader,
      WriteFileProcessor processor,
      ExceptionSkipPolicy skipPolicy,
      @Qualifier("InvoiceItemWriter") JdbcBatchItemWriter<Invoice> writer) {
    return stepBuilderFactory.get("writeFileStep")
        .allowStartIfComplete(true)
        .<Invoice, Invoice>chunk(1)
        .reader(reader)
        .processor(processor)
        .writer(writer)
        .faultTolerant()
        .skipPolicy(skipPolicy)
        .build();
  }

}
