package org.horace.shirotest.controller;

import lombok.RequiredArgsConstructor;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
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
        String passwordSalt = new SecureRandomNumberGenerator().nextBytes().toString();
        String encodingPassWord = new Sha256Hash("horace1234", passwordSalt, 2).toString();
        user.setPassword(encodingPassWord);
        user.setSalt(passwordSalt);
        user.setEncodeTime("2");
        userService.save(user);
        return JacksonUtil.toJson(user);
    }

}
