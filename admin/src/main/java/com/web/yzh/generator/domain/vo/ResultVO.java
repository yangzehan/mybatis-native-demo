package com.web.yzh.generator.domain.vo;

import cn.hutool.core.date.DateTime;
import com.web.yzh.Enum.ResultCodeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultVO<T> {
  int code;
  String msg;
  T content;
  Long timestamp;
  String traceDateTime;

  public ResultVO(int code, String msg, T content) {
    this.code = code;
    this.msg = msg;
    this.content = content;
    this.timestamp = System.currentTimeMillis();
    this.traceDateTime = DateTime.now().toString();
  }

  public ResultVO(int code, String msg) {
    this.code = code;
    this.msg = msg;
    this.content = null;
    this.timestamp = System.currentTimeMillis();
    this.traceDateTime = DateTime.now().toString();
  }

  public ResultVO(ResultCodeEnum resultCode, T content) {
    this.code = resultCode.getCode();
    this.msg = resultCode.getMsg();
    this.content = content;
    this.timestamp = System.currentTimeMillis();
    this.traceDateTime = DateTime.now().toString();
  }

  public ResultVO(T content) {
    this(ResultCodeEnum.SUCCESS, content);
  }
}
