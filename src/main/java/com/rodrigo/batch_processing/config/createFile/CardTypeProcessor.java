package com.rodrigo.batch_processing.config.createFile;

import com.rodrigo.batch_processing.domain.CardType;
import com.rodrigo.batch_processing.domain.FileControl;
import com.rodrigo.batch_processing.domain.StatusEnum;
import com.rodrigo.batch_processing.repositories.StatementHeaderRepository;
import com.rodrigo.batch_processing.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardTypeProcessor implements ItemProcessor<CardType, FileControl> {

  private final StatementHeaderRepository repository;

  private Long processingDate;
  private Long batchId;

  @BeforeStep
  public void beforeStep(StepExecution stepExecution) {
    batchId = stepExecution.getJobExecutionId();
    processingDate = Long.parseUnsignedLong(stepExecution.getJobParameters().getString("processingDate"));
  }

  @Override
  public FileControl process(@NonNull CardType item) throws Exception {
    var hasInvoicesToProcess = repository.findInvoices(processingDate, item.getProduct(), item.getVariant());

    if (hasInvoicesToProcess != null) {
      FileUtils.checkIfFileExists(item, batchId, processingDate.toString());

      var fileControl = FileControl.builder()
          .product(item.getProduct())
          .variant(item.getVariant())
          .fileName(FileUtils.fileName(item.getProduct(), item.getVariant(), batchId, processingDate.toString()))
          .status(StatusEnum.PROCESSING.toString())
          .build();

      return fileControl;
    }

    return null;
  }

}
