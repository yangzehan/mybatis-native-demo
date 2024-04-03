package org.dinky.natives.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.dinky.natives.admin.domain.Task;
import org.dinky.natives.admin.service.TaskService;
import org.dinky.natives.common.pojo.PageParam;
import org.dinky.natives.common.pojo.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author 杨泽翰
 */
@RestController
@RequestMapping("/flinkTask")
public class flinkTaskController {
  @Resource private TaskService taskService;

  @RequestMapping("/submitFlinkTask")
  public R<String> submitTask(Task task) {

    return R.success("ok");
  }

  @PostMapping("/saveFlinkTask")
  public R<Boolean> saveTask(@RequestBody Task task) {

    return R.success(taskService.save(task));
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

  @GetMapping("/getFlinkTask")
  public R<Task> getTask(Long id) {

    return R.success(taskService.getById(id));
  }

  @GetMapping("/getFlinkTaskList")
  public R<Page<Task>> getTaskList(PageParam pageParam) {

    return R.success(taskService.page(new Page<>(pageParam.getPageNo(), pageParam.getPageSize())));
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
