package com.rodrigo.batch_processing.config.createFile;

import javax.sql.DataSource;

import com.rodrigo.batch_processing.domain.FileControl;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileControlWriter {

  @Bean(name = "FileControlWriter")
  public JdbcBatchItemWriter<FileControl> writer(DataSource dataSource) {
    var sql = """
        INSERT INTO file_control (
          product, variant, file_name, status
        ) VALUES (
         :product, :variant, :fileName, :status
         )
        """;
    return new JdbcBatchItemWriterBuilder<FileControl>()
        .dataSource(dataSource)
        .sql(sql)
        .beanMapped()
        .build();
  }

}
