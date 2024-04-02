package com.web.yzh.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.yzh.exception.enums.ErrorCode;
import com.web.yzh.exception.enums.GlobalErrorCodeConstants;
import java.io.Serializable;
import java.util.Objects;
import lombok.Data;
import org.springframework.util.Assert;

/**
 * 通用返回
 *
 * @param <T> 数据泛型
 */
@Data
public class R<T> implements Serializable {

  /** 错误码 */
  private Integer code;

  /** 返回数据 */
  private T data;

  /** 错误提示，用户可阅读 */
  private String msg;

  /**
   * 将传入的 result 对象，转换成另外一个泛型结果的对象
   *
   * <p>因为 A 方法返回的 R 对象，不满足调用其的 B 方法的返回，所以需要进行转换。
   *
   * @param result 传入的 result 对象
   * @param <T> 返回的泛型
   * @return 新的 R 对象
   */
  public static <T> R<T> error(R<?> result) {
    return error(result.getCode(), result.getMsg());
  }

  public static <T> R<T> error(Integer code, String message) {
    Assert.isTrue(!GlobalErrorCodeConstants.SUCCESS.getCode().equals(code), "code 必须是错误的！");
    R<T> result = new R<>();
    result.code = code;
    result.msg = message;
    return result;
  }

  public static <T> R<T> error(ErrorCode errorCode) {
    return error(errorCode.getCode(), errorCode.getMsg());
  }

  public static <T> R<T> success(T data) {
    R<T> result = new R<>();
    result.code = GlobalErrorCodeConstants.SUCCESS.getCode();
    result.data = data;
    result.msg = "";
    return result;
  }

  public static boolean isSuccess(Integer code) {
    return Objects.equals(code, GlobalErrorCodeConstants.SUCCESS.getCode());
  }

  @JsonIgnore // 避免 jackson 序列化
  public boolean isSuccess() {
    return isSuccess(code);
  }

  @JsonIgnore // 避免 jackson 序列化
  public boolean isError() {
    return !isSuccess();
  }
}
