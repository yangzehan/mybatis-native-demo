package org.dinky.natives.admin.handler;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
  @Override
  public void insertFill(MetaObject metaObject) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    log.info("start insert fill ....");

    Object createBy = this.getFieldValByName("createBy", metaObject);
    if (createBy == null) {
      this.setFieldValByName("createBy", authentication.getName(), metaObject);
    }

    Object createTime = this.getFieldValByName("createTime", metaObject);
    if (createTime == null) {
      this.setFieldValByName("createTime", DateUtil.now(), metaObject);
    }

    this.setFieldValByName("updateBy", authentication.getName(), metaObject);
    this.setFieldValByName("updateTime", DateUtil.now(), metaObject);
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    this.setFieldValByName("updateBy", authentication.getName(), metaObject);
    this.setFieldValByName("updateTime", DateUtil.now(), metaObject);
  }
}
