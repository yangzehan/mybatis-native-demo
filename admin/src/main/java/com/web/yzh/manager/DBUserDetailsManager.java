package com.web.yzh.manager;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.web.yzh.exegesis.lambdaCapturingTypes;
import com.web.yzh.generator.domain.User;
import com.web.yzh.generator.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

/**
 * @author 杨泽翰
 */
@Slf4j
@Component
@lambdaCapturingTypes
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsService {
  @Resource private UserMapper mapper;

  @Override
  public void createUser(UserDetails user) {}

  @Override
  public void updateUser(UserDetails user) {}

  @Override
  public void deleteUser(String username) {}

  @Override
  public void changePassword(String oldPassword, String newPassword) {}

  @Override
  public boolean userExists(String username) {
    return false;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = mapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }
    log.info("用户登录: {}", user);
    return org.springframework.security.core.userdetails.User.withUsername(username)
        .password(user.getPassword())
        .build();
  }
}
