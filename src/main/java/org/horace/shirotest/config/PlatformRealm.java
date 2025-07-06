package org.horace.shirotest.config;

import lombok.RequiredArgsConstructor;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.lang.util.ByteSource;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.horace.shirotest.entity.Role;
import org.horace.shirotest.entity.User;
import org.horace.shirotest.service.RoleUserRelationService;
import org.horace.shirotest.service.UserService;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class PlatformRealm extends AuthorizingRealm {

    private final RoleUserRelationService roleUserRelationService;

    private final UserService userService;


    /**
     * 授权，指的是登录过后的赋予相关权限操作
     *
     * @param principalCollection 认证过后的用户信息
     * @return 返回组装好的授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User userInfo = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<Role> roleList = roleUserRelationService.findByUserName(userInfo.getUsername());
        Set<String> roleSet = roleList.stream().map(Role::getRoleName).collect(Collectors.toSet());
        info.setRoles(roleSet);
        return info;
    }

    /**
     * 登录认证的操作，为了相关登录的内容
     *
     * @param authenticationToken 提交过来的认证内容，对相关内容进行校验
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String submitUserName = authenticationToken.getPrincipal().toString();
        String submitPassword = authenticationToken.getCredentials().toString();
        User user = userService.getByUsername(submitUserName);
        //如果用户不存在直接报错
        if (Objects.isNull(user)) {
            return null;
        }
        String encodePassWord = new Sha256Hash(submitPassword, user.getSalt(), Integer.parseInt(user.getEncodeTime())).toString();
        if (!user.getPassword().equals(encodePassWord)) {
            return null;
        }
        //都认证成功才返回
        return new SimpleAuthenticationInfo(user, user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()), getName());
    }
}
