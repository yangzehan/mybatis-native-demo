package com.web.yzh.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.web.yzh.exegesis.lambdaCapturingTypes;
import com.web.yzh.generator.domain.User;
import com.web.yzh.generator.mapper.UserMapper;
import com.web.yzh.generator.service.UserService;
import com.web.yzh.manager.DBUserDetailsManager;
import com.web.yzh.pojo.R;
import org.springframework.beans.factory.annotation.Autowired;
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
  @Autowired private UserService service;
  @Autowired private UserMapper mapper;
  @Autowired private DBUserDetailsManager manager;

  @GetMapping("/test")
  public R<String> createUser() {

    return R.success("test");
  }

  @GetMapping("/test2")
  public R<Page<User>> testGet() {

    return R.success(
        service.page(
            new Page<>(1, 10), Wrappers.lambdaQuery(User.class).eq(User::getUsername, "admin")));
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
