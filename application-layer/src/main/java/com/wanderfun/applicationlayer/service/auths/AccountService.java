package com.wanderfun.applicationlayer.service.auths;

import com.wanderfun.applicationlayer.service.BaseService;
import com.wanderfun.domainlayer.model.auths.Account;

import java.util.List;

public interface AccountService extends BaseService<Account, Long> {
    Account findByEmail(String email);
    List<Account> findAllUserAccount();
    boolean updateAccountState(Long id);
}
