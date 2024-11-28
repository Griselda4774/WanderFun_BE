package com.project2.wanderfun.infrastructure.security;

import com.project2.wanderfun.domain.model.User;
import com.project2.wanderfun.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public UserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username){
        User user = userService.findUserByEmail(username);
        return new CustomUserDetails(user);
    }
}
