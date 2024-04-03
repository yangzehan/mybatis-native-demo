package org.dinky.natives.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.dinky.natives.common.pojo.BaseEntity;

/** 任务基础表 @TableName task */
@TableName(value = "task")
@Data
public class Task extends BaseEntity {

  /** 主键 */
  @TableId(type = IdType.AUTO)
  @JsonIgnore
  private Long id;

  /** 任务名称 */
  @NotNull(message = "名称不能为空")
  private String name;

  /** 任务备注 */
  private String remark;

  @NotNull(message = "类型不能为空")
  /** 任务类型 */
  private String taskType;

  /** 执行语句 */
  private String statement;
}
