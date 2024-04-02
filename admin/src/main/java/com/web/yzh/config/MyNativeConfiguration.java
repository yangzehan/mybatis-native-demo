package com.web.yzh.config;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;

/**
 * @author 杨泽翰
 */
@Configuration
@ImportRuntimeHints(MyNativeConfiguration.MyBaitsRuntimeHintsRegistrar.class)
public class MyNativeConfiguration {

  static class MyBaitsRuntimeHintsRegistrar implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {}
  }
}
