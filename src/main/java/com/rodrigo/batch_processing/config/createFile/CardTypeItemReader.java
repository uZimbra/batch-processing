package com.rodrigo.batch_processing.config.createFile;

import javax.sql.DataSource;

import com.rodrigo.batch_processing.domain.CardType;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CardTypeItemReader {

  @Bean("CardTypeReader")
  public JdbcCursorItemReader<CardType> cardTypeReader(DataSource dataSource) {
    var sql = "SELECT * FROM card_type";

    return new JdbcCursorItemReaderBuilder<CardType>()
        .name("jpaCursorItemReader")
        .dataSource(dataSource)
        .sql(sql)
        .beanRowMapper(CardType.class)
        .build();
  }

}
