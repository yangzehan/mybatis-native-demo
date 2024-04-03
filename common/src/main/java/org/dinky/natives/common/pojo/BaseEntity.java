package org.dinky.natives.common.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class BaseEntity {

  @TableField(fill = FieldFill.INSERT)
  @JsonIgnore
  /** 创建人 */
  private String createBy;

  @TableField(fill = FieldFill.INSERT)
  @JsonIgnore
  /** 创建时间 */
  private String createTime;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  @JsonIgnore
  /** 更新人 */
  private String updateBy;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  @JsonIgnore
  /** 更新时间 */
  private String updateTime;
}
