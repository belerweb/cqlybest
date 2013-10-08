package com.cqlybest.common.exception;

public abstract class AppException extends RuntimeException {

  private static final long serialVersionUID = 3651755374301291138L;

  public AppException() {
    super();
  }

  public AppException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public AppException(String message, Throwable cause) {
    super(message, cause);
  }

  public AppException(String message) {
    super(message);
  }

  public AppException(Throwable cause) {
    super(cause);
  }

}
