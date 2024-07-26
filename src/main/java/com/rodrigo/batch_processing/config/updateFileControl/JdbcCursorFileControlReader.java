package com.rodrigo.batch_processing.config.updateFileControl;

import com.rodrigo.batch_processing.domain.FileControl;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class JdbcCursorFileControlReader extends JdbcCursorItemReader<FileControl> implements StepExecutionListener {

  @Override
  public void beforeStep(@NonNull StepExecution stepExecution) {
    var batchId = stepExecution.getJobExecutionId();
    setPreparedStatementSetter(ps -> ps.setLong(1, batchId));
  }

  @Override
  @Nullable
  public ExitStatus afterStep(@NonNull StepExecution stepExecution) {
    return null;
  }

}
