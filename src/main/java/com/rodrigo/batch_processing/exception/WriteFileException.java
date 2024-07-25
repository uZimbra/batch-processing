package com.rodrigo.batch_processing.exception;

public class WriteFileException extends RuntimeException {

  public WriteFileException() {
    super();
  }

  public WriteFileException(String message) {
    super(message);
  }

  public WriteFileException(String message, Throwable cause) {
    super(message, cause);
  }

}
