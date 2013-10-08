package com.cqlybest.common.exception;

public class ForbiddenException extends AppException {

  private static final long serialVersionUID = -2863957310112379802L;

  public ForbiddenException() {
    super("没有权限访问");
  }

}
