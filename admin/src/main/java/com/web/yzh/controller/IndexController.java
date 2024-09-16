package com.web.yzh.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.web.yzh.exegesis.lambdaCapturingTypes;
import com.web.yzh.generator.domain.User;
import com.web.yzh.generator.mapper.UserMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杨泽翰
 */
@Tag(name = "测试接口")
@RestController
@lambdaCapturingTypes
public class IndexController {
  @Autowired private UserMapper mapper;

  @GetMapping("/test")
  public User createUser() {

    return mapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getUsername, "admin"));
  }
}
