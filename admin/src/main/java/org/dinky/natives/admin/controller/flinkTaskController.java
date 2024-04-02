package org.dinky.natives.admin.controller;

import org.dinky.natives.common.pojo.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杨泽翰
 */
@RestController
@RequestMapping("/flinkTask")
public class flinkTaskController {

  @RequestMapping("/submitFlinkTask")
  public R<String> submitTask() {

    return R.success("ok");
  }

  @RequestMapping("/stopFlinkTask")
  public R<String> stopTask() {

    return R.success("ok");
  }

  @RequestMapping("/restartFlinkTask")
  public R<String> restartTask() {

    return R.success("ok");
  }

  @RequestMapping("/deleteFlinkTask")
  public R<String> deleteTask() {

    return R.success("ok");
  }

  @RequestMapping("/getFlinkTask")
  public R<String> getTask() {

    return R.success("ok");
  }

  @RequestMapping("/getFlinkTaskList")
  public R<String> getTaskList() {

    return R.success("ok");
  }

  @RequestMapping("/getFlinkTaskLog")
  public R<String> getTaskLog() {

    return R.success("ok");
  }

  @RequestMapping("/getFlinkTaskStatus")
  public R<String> getTaskStatus() {

    return R.success("ok");
  }

  @RequestMapping("/getFlinkTaskConfig")
  public R<String> getTaskConfig() {

    return R.success("ok");
  }
}
