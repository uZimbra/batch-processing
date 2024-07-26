package com.rodrigo.batch_processing.config.updateFileControl;

import com.rodrigo.batch_processing.domain.FileControl;
import com.rodrigo.batch_processing.domain.StatusEnum;
import com.rodrigo.batch_processing.repositories.StatementHeaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateFileControlProcessor implements ItemProcessor<FileControl, FileControl> {

  private final StatementHeaderRepository statementHeaderRepository;

  private Long processingDate;

  @BeforeStep
  public void beforeStep(StepExecution stepExecution) {
    processingDate = Long.parseUnsignedLong(stepExecution.getJobParameters().getString("processingDate"));
  }

  @Override
  public FileControl process(@NonNull FileControl item) {
    var invoice = statementHeaderRepository.findIfHasInvoicesToProcess(processingDate, item.getProduct(),
        item.getVariant());

    if (invoice != null) {
      item.setStatus(StatusEnum.FAIL.name());

      return item;
    }

    item.setStatus(StatusEnum.SUCCESS.name());

    return item;
  }

}
