package com.rodrigo.batch_processing.tests.firstStep;

import javax.persistence.EntityManagerFactory;

import com.rodrigo.batch_processing.domain.CardType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FirstStepReader {

  private final EntityManagerFactory entityManagerFactory;

  public JpaCursorItemReader<CardType> read() {
    log.info("[READER] Lendo a tabela cardtype...");

    var sql = "SELECT ct FROM CardType ct";

    return new JpaCursorItemReaderBuilder<CardType>()
        .name("jpaCursorItemReaderListCardTypes")
        .entityManagerFactory(entityManagerFactory)
        .queryString(sql)
        .build();
  }

}