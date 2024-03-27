package com.web.yzh.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.yzh.generator.domain.vo.ResultVO;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author 杨泽翰
 */
@RestControllerAdvice(basePackages = {"com.web.yzh.controller"})
public class DefaultResponseBodyAdvice implements ResponseBodyAdvice {

  /**
   * 此组件是否支持给定的控制器方法返回类型和所选 HttpMessageConverter 类型。 形参: returnType – 返回类型 converterType – 选定的转换器类型
   * 返回值: true 如果 beforeBodyWrite 应该被调用; false 否则
   */
  @Override
  public boolean supports(MethodParameter returnType, Class converterType) {
    return !returnType.getGenericParameterType().equals(ResultVO.class);
  }

  /**
   * 在选择 an HttpMessageConverter 之后和调用其 write 方法之前调用。 形参: body – 要写的正文 returnType – 控制器方法的返回类型
   * selectedContentType – 通过内容协商选择的内容类型 selectedConverterType – 选择用于写入响应的转换器类型 request – 当前请求
   * response – 当前响应 返回值: 传入的正文或修改的（可能是新的）实例
   */
  @Override
  public Object beforeBodyWrite(
      Object body,
      MethodParameter returnType,
      MediaType selectedContentType,
      Class selectedConverterType,
      ServerHttpRequest request,
      ServerHttpResponse response) {
    // String类型不能直接包装，所以要进行些特别的处理
    if (returnType.getGenericParameterType().equals(String.class)) {
      ObjectMapper objectMapper = new ObjectMapper();
      try {
        // 将数据包装在ResultVO里后，再转换为json字符串响应给前端
        return objectMapper.writeValueAsString(new ResultVO<>(body));
      } catch (JsonProcessingException e) {
        throw new RuntimeException("返回String类型错误");
      }
    }

    // 将原本的数据包装在ResultVO里
    return new ResultVO<>(body);
  }
}
