package org.horace.shirotest.controller;

import lombok.RequiredArgsConstructor;
import org.horace.shirotest.entity.User;
import org.horace.shirotest.service.UserService;
import org.horace.shirotest.util.JacksonUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InitUserController {

    private final UserService userService;


    @GetMapping("/init")
    public String init() {
        User user = new User();
        user.setUsername("horace");
        //密码和盐的生成

        userService.save(user);
        return JacksonUtil.toJson(user);
    }

}
