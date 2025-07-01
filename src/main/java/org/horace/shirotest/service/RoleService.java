package org.horace.shirotest.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.horace.shirotest.entity.Role;
import org.horace.shirotest.mapper.RoleMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {
}
