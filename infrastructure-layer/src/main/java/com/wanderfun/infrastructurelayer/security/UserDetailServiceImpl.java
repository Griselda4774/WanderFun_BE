package com.wanderfun.infrastructurelayer.security;

import com.wanderfun.domainlayer.model.users.User;
import com.wanderfun.applicationlayer.service.UserService;
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
        User user = userService.findByEmail(username);
        return new CustomUserDetails(user);
    }
}
