package com.web.yzh.controller;

import cn.hutool.cache.impl.TimedCache;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.yzh.exegesis.lambdaCapturingTypes;
import com.web.yzh.pojo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杨泽翰
 */
@RestController
@lambdaCapturingTypes
public class userController {


@Autowired
private TimedCache<String,Object> timedCache;
@Autowired
private ObjectMapper objectMapper;
  @GetMapping("/test")
  public R<Object> test(String token)   {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    Object o = timedCache.get(token);

    return R.success(o);
  }


}
