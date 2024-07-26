package com.rodrigo.batch_processing.config.updateFileControl;

import javax.sql.DataSource;

import com.rodrigo.batch_processing.domain.FileControl;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateFileControlWriter {

  @Bean(name = "UpdateFileControlWriter")
  public JdbcBatchItemWriter<FileControl> writer(DataSource dataSource) {
    var sql = """
          UPDATE file_control SET
            product = :product,
            variant = :variant,
            status = :status,
            file_name = :fileName,
            batch_number = :batchNumber
          WHERE id = :id
        """;
    return new JdbcBatchItemWriterBuilder<FileControl>()
        .dataSource(dataSource)
        .sql(sql)
        .beanMapped()
        .build();
  }

}
