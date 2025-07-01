package org.horace.shirotest.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;

    //用户名
    private String username;

    //密码
    private String password;

    //加密的盐
    private String salt;

    //加密次数
    private String encodeTime;

    @TableField(exist = false)
    private List<String> roleIds;
}
