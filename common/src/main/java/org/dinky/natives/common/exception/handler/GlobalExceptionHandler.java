package org.dinky.natives.common.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.dinky.natives.common.exception.BusinessException;
import org.dinky.natives.common.pojo.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 杨泽翰
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  /** 业务异常 */
  @ExceptionHandler(BusinessException.class)
  public R businessException(BusinessException e) {
    log.warn(e.getMessage(), e);

    return R.error(e.getErrorCode());
  }
}
