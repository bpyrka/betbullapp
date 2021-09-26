package com.assessment.betbull.betbull.Exception;

import lombok.Getter;

public class BusinessException extends Exception {

  @Getter
  private final ErrorCode code;

  public BusinessException(final ErrorCode code,
                           final String message) {
    super(message);
    this.code = code;
  }

  public BusinessException(final ErrorCode code) {
    this(code,
         null);
  }

}
