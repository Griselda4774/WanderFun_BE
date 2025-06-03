package com.wanderfun.domainlayer.repository.auths;

import com.wanderfun.domainlayer.model.auths.Account;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends BaseRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
    List<Account> findAllUserAccount();
    boolean updateAccountState(Long id);
}
