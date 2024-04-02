package org.dinky.natives.common.exception;

import lombok.Getter;
import org.dinky.natives.common.exception.enums.ErrorCode;

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
