package com.wanderfun.applicationlayer.service.auths;

import com.wanderfun.applicationlayer.service.BaseService;
import com.wanderfun.domainlayer.model.auths.Account;

public interface AccountService extends BaseService<Account, Long> {
    Account findByEmail(String email);
}
