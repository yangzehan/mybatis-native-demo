package com.web.yzh.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 处理登录失败异常
 *
 * @author 杨泽翰
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
      throws IOException, ServletException {

    // 获取错误信息
    String localizedMessage = exception.getLocalizedMessage();

    // 创建结果对象
    HashMap<String, Object> result = new HashMap<>();
    result.put("code", -1);
    result.put("message", localizedMessage);
    ObjectMapper objectMapper = new ObjectMapper();

    // 转换成json字符串
    String json = objectMapper.writeValueAsString(result);

    // 返回响应
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().println(json);
  }
}
