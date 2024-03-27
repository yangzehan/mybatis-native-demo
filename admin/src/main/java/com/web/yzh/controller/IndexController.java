package com.web.yzh.controller;

import com.web.yzh.exegesis.lambdaCapturingTypes;
import com.web.yzh.generator.mapper.UserMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杨泽翰
 */
@Tag(name = "首页模块")
@RestController
@lambdaCapturingTypes
public class IndexController {
  @Autowired private UserMapper mapper;

  @GetMapping("/test")
  public String createUser() {

    return "test";
  }

  @GetMapping("/test2")
  public String testGet() {
    return "/test2";
  }
}
