package com.web.yzh.Enum;

import lombok.Getter;

/**
 * @author 杨泽翰
 */
@Getter
public enum ResultCodeEnum {
  SUCCESS(0, "操作成功"),
  VERITY_GEN_ERROR(9801, "验证码生成失败"),
  VERITY_IDENTITY_ERROR(9803, "验证码验证失败"),
  TOKEN_NULL(9777, "请求头里没有token"),
  TOKEN_TIME_OUT(9776, "token已过期,请重新登录"),
  TOKEN_USER_ALREADY_SIGN(9775, "token用户已登录,请退出重登后再请求本接口"),
  FAILED(9500, "响应失败"),
  BUSY(9600, "系统繁忙"),
  ACCOUNT_PASS_FAILED(9301, "密码错误"),
  ACCOUNT_NOT_EXIST(9302, "用户名不存在"),
  ACCOUNT_DISABLED(9303, "用户没有权限/已禁用"),
  NOT_LOGIN(9403, "未登录"),
  REQ_BODY_IS_NULL(9995, "requestBody请求体异常"),
  VALIDATE_FAILED(9996, "参数校验异常"),
  ARITHMETIC_FAILED(9997, "算术异常"),
  ARRAY_INDEX_OUT_OF_BOUNDS_FAILED(9998, "数组越界异常"),
  USER_IS_USING(7701, "目标用户正在使用中"),
  IllEGAL_ARG_EXCEPTION(9988, "非法参数异常"),
  ERROR(9999, "未知错误");

  private int code;
  private String msg;

  ResultCodeEnum(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}
