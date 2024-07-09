package com.web.yzh.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.web.yzh.exegesis.lambdaCapturingTypes;
import com.web.yzh.generator.domain.User;
import com.web.yzh.generator.mapper.UserMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杨泽翰
 */
@Tag(name = "测试接口")
@Controller
@lambdaCapturingTypes
public class IndexController {
  @Autowired private UserMapper mapper;

@GetMapping("/")
  public String index() {
    return "index";
  }
 @GetMapping("/login")
  public String login() {
    return "login";
  }
}
