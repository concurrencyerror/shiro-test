package org.horace.shirotest.config;

import lombok.RequiredArgsConstructor;
import org.horace.shirotest.entity.User;
import org.horace.shirotest.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Objects;

@RequiredArgsConstructor
@Configuration
public class UserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetail(user);
    }
}
