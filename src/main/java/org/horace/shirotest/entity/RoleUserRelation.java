package org.horace.shirotest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("role_user_relation")
public class RoleUserRelation {
    private Long id;
    private String roleId;
    private String username;
}
