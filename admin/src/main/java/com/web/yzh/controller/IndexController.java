package com.web.yzh.controller;

import com.web.yzh.exegesis.lambdaCapturingTypes;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 杨泽翰
 */
@Tag(name = "首页模块")
@Controller
@lambdaCapturingTypes
public class IndexController {

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }
}
