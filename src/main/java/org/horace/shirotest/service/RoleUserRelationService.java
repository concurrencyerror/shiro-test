package org.horace.shirotest.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.horace.shirotest.entity.RoleUserRelation;
import org.horace.shirotest.mapper.RoleUserRelationMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleUserRelationService extends ServiceImpl<RoleUserRelationMapper, RoleUserRelation> {
}
