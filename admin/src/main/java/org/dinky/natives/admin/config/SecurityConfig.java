package org.dinky.natives.admin.config;

import static org.springframework.security.config.Customizer.withDefaults;

import jakarta.annotation.Resource;
import org.dinky.natives.admin.handler.MyAuthenticationFailureHandler;
import org.dinky.natives.admin.handler.MyAuthenticationSuccessHandler;
import org.dinky.natives.admin.handler.MyLogoutSuccessHandler;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security配置类
 *
 * @author 杨泽翰
 */
@Configuration
@ImportRuntimeHints(SecurityConfig.SecurityHintsRegistrar.class)
public class SecurityConfig {
  @Resource private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
  @Resource private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
  @Resource private MyLogoutSuccessHandler myLogoutSuccessHandler;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // authorizeRequests()：开启授权保护
    // anyRequest()：对所有请求开启授权保护
    // authenticated()：已认证请求会自动被授权
    http.authorizeHttpRequests(
            authorize ->
                authorize
                    .requestMatchers(HttpMethod.POST, "/demo/add")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        // 表单授权方式
        .formLogin(withDefaults())
        // 基本授权方式
        .httpBasic(withDefaults());
    // 表单登录配置
    http.formLogin(
        form -> {
          form.loginPage("/login")
              .permitAll() // 登录页面无需授权即可访问
              .usernameParameter("username") // 自定义表单用户名参数，默认是username
              .passwordParameter("password") // 自定义表单密码参数，默认是password
              .failureUrl("/login?error") // 登录失败的返回地址
              .successHandler(this.myAuthenticationSuccessHandler) // 登录成功请求监听
              .failureHandler(this.myAuthenticationFailureHandler); // 登录失败请求监听
        }); // 使用表单授权方式

    http.logout(
        logout -> {
          logout.logoutSuccessHandler(this.myLogoutSuccessHandler);
        });
    // 关闭csrf攻击防御
    http.csrf(AbstractHttpConfigurer::disable);
    return http.build();
  }

  public static class SecurityHintsRegistrar implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
      hints.serialization().registerType(User.class);
    }
  }
}
