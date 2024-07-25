package com.rodrigo.batch_processing.config;

import com.rodrigo.batch_processing.exception.WriteFileException;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.stereotype.Component;

@Component
public class ExceptionSkipPolicy implements SkipPolicy {

  @Override
  public boolean shouldSkip(Throwable t, int skipCount) throws SkipLimitExceededException {
    if (t instanceof WriteFileException) {
      return true;
    }
    return false;
  }

}
