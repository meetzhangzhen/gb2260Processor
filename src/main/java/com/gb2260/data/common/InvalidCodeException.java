package com.gb2260.data.common;

public class InvalidCodeException extends RuntimeException {

  public InvalidCodeException(String s) {
    super(s, new Throwable());
  }
}
