package com.web.yzh.handler;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 处理登录成功监听
 *
 * @author 杨泽翰
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
@Autowired
TimedCache timedCache;
  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {



    // 获取用户身份信息
    Object principal = authentication.getPrincipal();

    // 创建结果对象
    HashMap<String, Object> result = new HashMap<>();
    result.put("code", 0);
    result.put("message", "登录成功");
    result.put("data", principal);
    ObjectMapper objectMapper = new ObjectMapper();
    String key = IdUtil.fastSimpleUUID();
    result.put("token",key);
    // 转换成json字符串
    String json = objectMapper.writeValueAsString(result);



    timedCache.put(key, principal);
    // 返回响应
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().println(json);
  }
}
