package com.wanderfun.infrastructurelayer.repository.auths;

import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.auths.Account;
import com.wanderfun.domainlayer.repository.auths.AccountRepository;
import com.wanderfun.infrastructurelayer.persistence.entity.auths.AccountEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.auths.JpaAccountRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Override
    public List<Account> findAllUserAccount() {
        return jpaAccountRepository.findAllUserAccount()
                .stream()
                .map(entity -> objectMapper.map(entity, Account.class))
                .toList();
    }

    @Override
    public boolean updateAccountState(Long id) {
        Optional<AccountEntity> accountEntityOptional = jpaAccountRepository.findById(id);
        if (accountEntityOptional.isPresent()) {
            AccountEntity accountEntity = accountEntityOptional.get();
            accountEntity.setActive(!accountEntity.isActive());
            jpaAccountRepository.save(accountEntity);
            return true;
        } else {
            throw new ObjectNotFoundException(String.format("%s with id %d not found", Account.class.getSimpleName(), id));
        }
    }

    @Override
    public Long countAccountsCreatedToday() {
        return jpaAccountRepository.countAccountsCreatedToday();
    }
}
