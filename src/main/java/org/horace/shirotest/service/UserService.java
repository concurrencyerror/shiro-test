package org.horace.shirotest.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.horace.shirotest.entity.User;
import org.horace.shirotest.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}
