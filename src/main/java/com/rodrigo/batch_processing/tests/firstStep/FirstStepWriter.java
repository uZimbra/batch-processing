package com.rodrigo.batch_processing.tests.firstStep;

import java.util.List;

import com.rodrigo.batch_processing.domain.CardType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FirstStepWriter implements ItemWriter<CardType> {

  // private final StepExecution stepExecution;

  @Override
  public void write(List<? extends CardType> items) throws Exception {
    CardType item = items.get(0);

    log.info("[WRITER] escrevendo o arquivo para o documento {}", item);
  }

}
