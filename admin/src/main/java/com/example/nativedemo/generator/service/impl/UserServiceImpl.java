package com.example.nativedemo.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.nativedemo.generator.domain.User;
import com.example.nativedemo.generator.service.UserService;
import com.example.nativedemo.generator.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 杨泽翰
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-04-03 10:06:22
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




