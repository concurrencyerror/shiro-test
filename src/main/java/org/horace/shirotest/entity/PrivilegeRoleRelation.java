package org.horace.shirotest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("privilege_relation")
public class PrivilegeRoleRelation {
    private String id;

    private String roleId;

    private String privilege;
}
