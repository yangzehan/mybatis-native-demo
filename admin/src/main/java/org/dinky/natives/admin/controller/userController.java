package org.dinky.natives.admin.controller;

import org.dinky.natives.admin.manager.DBUserDetailsManager;
import org.dinky.natives.common.exegesis.lambdaCapturingTypes;
import org.dinky.natives.common.pojo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杨泽翰
 */
@RestController
@lambdaCapturingTypes
public class userController {

  @Autowired private DBUserDetailsManager manager;

  @GetMapping("/test")
  public R<String> createUser() {

    return R.success("test");
  }

  @PostMapping("/add")
  public R<String> add(@RequestBody User user) {

    manager.createUser(
        org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
            .password(user.getPassword())
            .build());

    return R.success("创建成功");
  }
}
