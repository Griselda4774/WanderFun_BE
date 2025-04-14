package com.wanderfun.infrastructurelayer.repository.account;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.accounts.Account;
import com.wanderfun.domainlayer.repository.account.AccountRepository;
import com.wanderfun.infrastructurelayer.persistence.entity.accounts.AccountEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.accounts.JpaAccountRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccountRepositoryImpl extends BaseRepositoryImpl<Account, AccountEntity, Long> implements AccountRepository {
    private final JpaAccountRepository jpaAccountRepository;

    @Autowired
    public AccountRepositoryImpl(JpaAccountRepository jpaAccountRepository, ObjectMapper objectMapper) {
        super(jpaAccountRepository, objectMapper, Account.class, AccountEntity.class);
        this.jpaAccountRepository = jpaAccountRepository;
    }

    @Override
    public Optional<Account> findByEmail (String email) {
        return jpaAccountRepository.findByEmail(email)
                    .map(entity -> objectMapper.map(entity, Account.class));
    }
}
