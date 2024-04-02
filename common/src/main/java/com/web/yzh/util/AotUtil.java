package com.web.yzh.util;

import com.web.yzh.exegesis.lambdaCapturingTypes;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * @author 杨泽翰
 */
@Slf4j
public class AotUtil {
  public static Set<BeanDefinition> getBeanDefinitions(String basePackage) {
    ClassPathScanningCandidateComponentProvider scanner =
        new ClassPathScanningCandidateComponentProvider(false);
    AnnotationTypeFilter filter = new AnnotationTypeFilter(lambdaCapturingTypes.class);
    scanner.addIncludeFilter(filter);
    return scanner.findCandidateComponents(basePackage);
  }

  public static List<String> getLambdaAotClass(List<String> basePackagelist) {
    List<String> getLambdaAotClasses = new ArrayList<>();
    basePackagelist.forEach(
        basePackage -> {
          Set<BeanDefinition> beanDefinitions = getBeanDefinitions(basePackage);
          if (beanDefinitions.isEmpty()) {
            log.info("没有使用lambda表达式的类");
          } else {
            beanDefinitions.forEach(
                beanDefinition -> {
                  log.info("找到{}", beanDefinition.getBeanClassName());

                  getLambdaAotClasses.add(beanDefinition.getBeanClassName());
                });
          }
        });
    return getLambdaAotClasses;
  }

  public static void main(String[] args) {
    List<String> basePackagelist = new ArrayList<>();
    basePackagelist.add("com.web.yzh");
    List<String> lambdaAotClass = getLambdaAotClass(basePackagelist);
    System.out.println(lambdaAotClass);
  }
}
