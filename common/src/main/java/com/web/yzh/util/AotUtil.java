package com.web.yzh.util;

import com.web.yzh.exegesis.lambdaCapturingTypes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.AntPathMatcher;

/**
 * @author 杨泽翰
 */
@Slf4j
public class AotUtil {
  public static <T> Set<BeanDefinition> getBeanDefinitions(String basePackage, Class<? extends Annotation> annotationType) {
    ClassPathScanningCandidateComponentProvider scanner =
        new ClassPathScanningCandidateComponentProvider(false);
    AnnotationTypeFilter filter = new AnnotationTypeFilter(annotationType);
    scanner.addIncludeFilter(filter);
    return scanner.findCandidateComponents(basePackage);
  }

  public static List<String> getLambdaAotClass(List<String> basePackagelist) {
    List<String> getLambdaAotClasses = new ArrayList<>();
    basePackagelist.forEach(
        basePackage -> {
          Set<BeanDefinition> beanDefinitions = getBeanDefinitions(basePackage, lambdaCapturingTypes.class);
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

    public static <T> Set<BeanDefinition> getBeanDefinitions(String basePackage) {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(true);
        return scanner.findCandidateComponents(basePackage);
    }
    public static List<String> getLambdaAotClass2(List<String> basePackagelist,Class app) {
        List<String> getLambdaAotClasses = new ArrayList<>();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        MapperScan annotation = AnnotationUtils.getAnnotation(app, MapperScan.class);
        basePackagelist.forEach(
                basePackage -> {
                    Set<BeanDefinition> beanDefinitions = getBeanDefinitions(basePackage);
                    beanDefinitions.forEach(beanDefinition -> {
                        Field[] declaredFields;
                        try {
                            declaredFields = Class.forName(beanDefinition.getBeanClassName()).getDeclaredFields();
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        if (declaredFields.length==0){
                            return;
                        }
                        Optional<String> first = Arrays.stream(annotation.basePackages()).filter(packageName -> {
                            for (int i = 0; i < declaredFields.length; i++) {
                                if (antPathMatcher.match(packageName, declaredFields[i].getType().getPackageName())) {
                                    return true;
                                }
                            }
                            return false;
                        }).findFirst();
                        if (first.isPresent()) {
                            log.info("找到一个使用mapper的类：{}", beanDefinition.getBeanClassName());
                            getLambdaAotClasses.add(beanDefinition.getBeanClassName());
                        }
                    });
                });
        return getLambdaAotClasses;
    }




    public static List<String> getAutowiredAotClass(List<String> basePackagelist) {
        List<String> autowiredAotClass = new ArrayList<>();
        basePackagelist.forEach(
                basePackage -> {
                    Set<BeanDefinition> beanDefinitions = getBeanDefinitions(basePackage, Autowired.class);
                    if (beanDefinitions.isEmpty()) {
                        log.info("没有使用Autowired注释的类");
                    } else {
                        beanDefinitions.forEach(
                                beanDefinition -> {
                                    log.info("找到{}", beanDefinition.getBeanClassName());

                                    autowiredAotClass.add(beanDefinition.getBeanClassName());
                                });
                    }
                });
        return autowiredAotClass;
    }


  public static void main(String[] args) {
    List<String> basePackagelist = new ArrayList<>();
    basePackagelist.add("com.web.yzh");
    List<String> lambdaAotClass = getLambdaAotClass(basePackagelist);
    System.out.println(lambdaAotClass);
  }
}
