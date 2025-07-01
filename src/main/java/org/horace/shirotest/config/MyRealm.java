package org.horace.shirotest.config;

import com.google.common.base.Strings;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    Map<String, String> userMap = new HashMap<>();

    public static String passwordSalt = new SecureRandomNumberGenerator().nextBytes().toString();

    {
        super.setName("myRealm");
        userMap.put("root", "root1234");
    }

    /**
     * 授权
     *
     * @param principals the primary identifying principals of the AuthorizationInfo that should be retrieved.
     * @return 返回授权的相关信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //根据已经认证过的用户信息获取相关的权限和级别
        //String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //为了试验，写死相关数据
        Set<String> roleSet = new HashSet<>();
        roleSet.add("admin");
        roleSet.add("user");
        info.setRoles(roleSet);
        Set<String> permissionSet = new HashSet<>();
        permissionSet.add("user:delete");
        permissionSet.add("user:add");
        info.setStringPermissions(permissionSet);
        return info;
    }

    /**
     * 认证
     *
     * @param token the authentication token containing the user's principal and credentials.
     * @return 返回认证后的信息
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        String password = String.valueOf((char[]) token.getCredentials());
        String savePassWord = userMap.get(userName);
        if (Strings.isNullOrEmpty(savePassWord)) {
            return null;
        }
        String encodingPassWord = new Sha256Hash(savePassWord, passwordSalt, 2).toString();
        if (!password.equals(encodingPassWord)) {
            return null;
        }
        return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), getName());
    }
}
