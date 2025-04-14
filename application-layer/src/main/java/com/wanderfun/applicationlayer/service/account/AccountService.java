package com.wanderfun.applicationlayer.service.account;

import com.wanderfun.domainlayer.model.accounts.Account;
import com.wanderfun.domainlayer.model.users.User;

public interface AccountService {
    Account findByEmail(String email);
}
