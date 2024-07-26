package com.rodrigo.batch_processing.config.sanetizeDirectory;

import java.io.File;

import com.rodrigo.batch_processing.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SanetizeDirectoryTask implements Tasklet {

  @Override
  public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext)
      throws Exception {
    var files = FileUtils.listFilesInOutputDirectory();

    for (File f : files) {
      log.info("[l23] Deleting previously output processing file: {}", f.getName());

      if (!f.delete()) {
        throw new UnexpectedJobExecutionException("Falha ao remover arquivo: " + f.getName());
      }
    }

    return RepeatStatus.FINISHED;
  }

}
