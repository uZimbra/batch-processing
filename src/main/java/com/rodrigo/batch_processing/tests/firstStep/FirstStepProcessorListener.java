package com.rodrigo.batch_processing.tests.firstStep;

import com.rodrigo.batch_processing.domain.CardType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FirstStepProcessorListener implements ItemProcessListener<CardType, CardType> {

  @Override
  public void beforeProcess(@NonNull CardType item) {
    log.info("[LISTENER] Consultando tabela de controle do arquivo para o cartão {}", item);
  }

  @Override
  public void afterProcess(CardType item, @Nullable CardType result) {
    log.info("[LISTENER] Finalizando pre-processamento...");
  }

  @Override
  public void onProcessError(CardType item, Exception e) {
  }

}
