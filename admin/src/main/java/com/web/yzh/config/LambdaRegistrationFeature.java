package com.web.yzh.config;

import static com.web.yzh.util.AotUtil.getLambdaAotClass;
import static java.lang.Class.forName;

import java.util.ArrayList;
import java.util.List;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeSerialization;

/**
 * lambda 表达式注入到graal中
 *
 * @author ztp
 * @date 2023/8/18 11:53
 */
public class LambdaRegistrationFeature implements Feature {

  @Override
  public void duringSetup(DuringSetupAccess access) {
    // TODO 这里需要将lambda表达式所使用的成员类都注册上来,具体情况视项目情况而定,一般扫描@Controller和@Service的会多点.
    List<String> basePackagelist = new ArrayList<>();
    basePackagelist.add("com.web.yzh");
    List<String> lambdaAotClass = getLambdaAotClass(basePackagelist);
    lambdaAotClass.forEach(
        s -> {
          try {
            RuntimeSerialization.registerLambdaCapturingClass(forName(s));
          } catch (ClassNotFoundException ignored) {
          }
        });
  }
}
