package org.dinky.natives.admin.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.dinky.natives.common.exegesis.lambdaCapturingTypes;
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
