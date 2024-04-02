package org.dinky.natives.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dinky.natives.admin.domain.User;
import org.dinky.natives.admin.mapper.UserMapper;
import org.dinky.natives.admin.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 杨泽翰
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-03-20 17:24:13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {}
