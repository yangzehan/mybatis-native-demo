package com.web.yzh.exception.handler;

import com.web.yzh.exception.BusinessException;
import com.web.yzh.pojo.R;
import lombok.extern.slf4j.Slf4j;
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
