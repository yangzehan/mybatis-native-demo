package com.web.yzh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 杨泽翰
 */
@SpringBootApplication(proxyBeanMethods = false)
@MapperScan(
    basePackages = "com.web.yzh.generator.mapper",
    sqlSessionTemplateRef = "sqlSessionTemplate")
public class NativeDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(NativeDemoApplication.class, args);
  }
}
