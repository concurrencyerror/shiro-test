package org.horace.shirotest.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.horace.shirotest.entity.Role;
import org.horace.shirotest.entity.RoleUserRelation;
import org.horace.shirotest.mapper.RoleUserRelationMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleUserRelationService extends ServiceImpl<RoleUserRelationMapper, RoleUserRelation> {

    private final RoleService roleService;

    public List<Role> findByUserName(String userName) {
        if (Strings.isNullOrEmpty(userName)) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<RoleUserRelation> wrapper = Wrappers.lambdaQuery(RoleUserRelation.class)
                .eq(RoleUserRelation::getUsername, userName)
                .select();
        List<RoleUserRelation> relations = getBaseMapper().selectList(wrapper);
        Set<String> roleId = relations.stream().map(RoleUserRelation::getRoleId).collect(Collectors.toSet());
        LambdaQueryWrapper<Role> roleIdWrapper = Wrappers.lambdaQuery(Role.class)
                .in(!roleId.isEmpty(), Role::getId, roleId)
                .select();
        return roleService.list(roleIdWrapper);
    }
}
