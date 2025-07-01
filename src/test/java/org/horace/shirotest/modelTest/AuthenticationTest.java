package org.horace.shirotest.modelTest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.horace.shirotest.config.MyRealm;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthenticationTest {

    MyRealm myRealm = new MyRealm();

    String password;


    @Before
    public void addUser() {
        String password = "root1234";
        int time = 2;
        this.password = new Sha256Hash(password, MyRealm.passwordSalt, time).toString();
    }

    @Test
    public void testAuthentication() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager(myRealm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("root", password);
        subject.login(token);
        System.out.println("isAuthenticated:" + subject.isAuthenticated());
        subject.checkRole("admin");
        subject.checkRole("user");
        subject.checkPermission("user:add");
        subject.logout();
    }
}
