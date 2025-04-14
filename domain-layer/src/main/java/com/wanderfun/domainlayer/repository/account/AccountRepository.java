package com.wanderfun.domainlayer.repository.account;

import com.wanderfun.domainlayer.model.accounts.Account;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.Optional;

public interface AccountRepository extends BaseRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
}
