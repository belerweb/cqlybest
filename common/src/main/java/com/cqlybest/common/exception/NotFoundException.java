package com.cqlybest.common.exception;

public class NotFoundException extends AppException {

  private static final long serialVersionUID = -8103484799081810039L;

  public NotFoundException() {
    super("网页不存在");
  }

}
