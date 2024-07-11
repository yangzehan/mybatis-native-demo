package com.web.yzh.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.yzh.NativeDemoApplication;
import com.web.yzh.controller.IndexController;
import com.web.yzh.controller.userController;
import com.web.yzh.util.AotUtil;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

import static com.web.yzh.util.AotUtil.getLambdaAotClass2;

@SpringBootTest
class AdminApplicationTests {



  @Test
  void testPassword()  {
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    MapperScan annotation = AnnotationUtils.getAnnotation(NativeDemoApplication.class, MapperScan.class);
    String[] basePackages = annotation.basePackages();
    Field[] fields = IndexController.class.getDeclaredFields();
    for (int i = 0; i < fields.length; i++) {
      String packageName = fields[i].getType().getPackageName();
      Optional<String> first = Arrays.stream(basePackages).filter(s -> antPathMatcher.match(s,packageName)).findFirst();
      if(first.isPresent()){
        System.out.println(first.get());
      }
    }
  }
  @Test
  void testAot()  {
    List<String> basePackagelist = new ArrayList<>();
    basePackagelist.add("com.web.yzh");
    getLambdaAotClass2(basePackagelist, NativeDemoApplication.class);
}
}
