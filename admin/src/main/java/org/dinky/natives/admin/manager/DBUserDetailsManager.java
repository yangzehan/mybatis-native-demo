package org.dinky.natives.admin.manager;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.dinky.natives.admin.domain.User;
import org.dinky.natives.admin.mapper.UserMapper;
import org.dinky.natives.common.exception.BusinessException;
import org.dinky.natives.common.exegesis.lambdaCapturingTypes;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

/**
 * @author 杨泽翰
 */
@Slf4j
@lambdaCapturingTypes
@Component
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsService {
  @Getter
  private final UserCache userCache = new SpringCacheBasedUserCache(new ConcurrentMapCache("user"));
  @Resource private UserMapper mapper;

  @Override
  public void createUser(UserDetails userDetails) {
    UserDetails cacheUser = this.getUserCache().getUserFromCache(userDetails.getUsername());
    User dBuser =
        mapper.selectOne(
            Wrappers.<User>lambdaQuery().eq(User::getUsername, userDetails.getUsername()));
    if (cacheUser != null || dBuser != null) {
      log.warn("用户已存在");
      throw new BusinessException(500, "用户已存在");
    }
    User user = new User();
    user.setUsername(userDetails.getUsername());
    user.setPassword(userDetails.getPassword());
    user.setEnabled(userDetails.isEnabled() ? 0 : 1);
    mapper.insert(user);
  }

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
    UserDetails userCache = this.getUserCache().getUserFromCache(username);
    if (userCache != null) {
      return userCache;
    }
    User user = mapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }
    log.info("用户登录: {}", user);
    UserDetails userDetails =
        org.springframework.security.core.userdetails.User.withUsername(username)
            .password(user.getPassword())
            .build();
    this.getUserCache().putUserInCache(userDetails);
    return userDetails;
  }
}
