package org.horace.shirotest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    private String id;

    //用户名
    private String username;

    //密码
    private String password;

    //加密的盐
    private String salt;

    //加密次数
    private String enCodeTime;


}
