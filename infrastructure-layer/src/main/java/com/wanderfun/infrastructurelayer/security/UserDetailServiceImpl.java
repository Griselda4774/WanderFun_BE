package com.wanderfun.infrastructurelayer.security;

import com.wanderfun.applicationlayer.service.auths.AccountService;
import com.wanderfun.domainlayer.model.auths.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final AccountService accountService;

    @Autowired
    public UserDetailServiceImpl(AccountService accountService) {
        super();
        this.accountService = accountService;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username){
        Account account = accountService.findByEmail(username);
        return new CustomUserDetails(account);
    }
}
