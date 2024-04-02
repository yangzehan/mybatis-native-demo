package org.dinky.natives.admin.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import org.dinky.natives.admin.manager.DBUserDetailsManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 登出监听
 *
 * @author 杨泽翰
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
  @Resource private DBUserDetailsManager manager;

  @Override
  public void onLogoutSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {

    manager.getUserCache().removeUserFromCache(authentication.getName());

    // 创建结果对象
    HashMap<String, Object> result = new HashMap<>();
    result.put("code", 0);
    result.put("message", "注销成功");
    ObjectMapper objectMapper = new ObjectMapper();

    // 转换成json字符串
    String json = objectMapper.writeValueAsString(result);

    // 返回响应
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().println(json);
  }
}
