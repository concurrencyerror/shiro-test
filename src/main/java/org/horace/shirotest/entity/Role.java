package org.horace.shirotest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("role")
public class Role {
    private String id;

    private String roleName;

    private String userId;

}
