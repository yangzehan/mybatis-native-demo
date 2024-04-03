package org.dinky.natives.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dinky.natives.admin.domain.Task;
import org.dinky.natives.admin.service.TaskService;
import org.dinky.natives.admin.mapper.TaskMapper;
import org.springframework.stereotype.Service;

/**
* @author yangzehan
* @description 针对表【task(任务基础表)】的数据库操作Service实现
* @createDate 2024-04-02 22:56:54
*/
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task>
    implements TaskService{

}




