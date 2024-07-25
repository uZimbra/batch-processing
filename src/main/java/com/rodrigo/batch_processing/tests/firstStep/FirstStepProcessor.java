package com.rodrigo.batch_processing.tests.firstStep;

import com.rodrigo.batch_processing.domain.CardType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FirstStepProcessor implements ItemProcessor<CardType, CardType> {

  @Override
  public CardType process(CardType item) throws Exception {
    log.info("[PROCESSOR] processando dados para o cartao {}", item);

    return item;
  }

}
