package com.rodrigo.batch_processing.config.writeFile;

import com.rodrigo.batch_processing.domain.Invoice;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.lang.Nullable;

public class JdbcCursorInvoiceReader extends JdbcCursorItemReader<Invoice> implements StepExecutionListener {

  @Override
  public void beforeStep(StepExecution stepExecution) {
    var processingDate = Long.parseUnsignedLong(stepExecution.getJobParameters().getString("processingDate"));
    setPreparedStatementSetter(ps -> ps.setLong(1, processingDate));
  }

  @Override
  @Nullable
  public ExitStatus afterStep(StepExecution stepExecution) {
    return null;
  }

}
