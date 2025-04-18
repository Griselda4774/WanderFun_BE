package com.wanderfun.infrastructurelayer.service.auths;

import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.auths.AccountService;
import com.wanderfun.domainlayer.model.auths.Account;
import com.wanderfun.domainlayer.repository.auths.AccountRepository;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account, Long> implements AccountService{
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, ObjectMapper objectMapper) {
        super(accountRepository, objectMapper, Account.class);
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", Account.class.getSimpleName())));
    }
}
