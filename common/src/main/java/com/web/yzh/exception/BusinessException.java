package com.web.yzh.exception;

import com.web.yzh.exception.enums.ErrorCode;
import lombok.Getter;

/**
 * @author 杨泽翰
 */
@Getter
public class BusinessException extends RuntimeException {

  private ErrorCode errorCode;

  public BusinessException(ErrorCode errorCode) {

    this.errorCode = errorCode;
  }

  public BusinessException(Integer code, String msg) {

    this.errorCode = new ErrorCode(code, msg);
  }

  @Override
  public String getMessage() {
    return errorCode.getMsg();
  }
}
