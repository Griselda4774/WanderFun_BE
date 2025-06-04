package com.wanderfun.infrastructurelayer.usecase;

import com.wanderfun.applicationlayer.dto.auths.AccountDto;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.auths.AccountService;
import com.wanderfun.applicationlayer.usecase.AccountUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountUsecaseImpl implements AccountUsecase {
    private AccountService accountService;
    private final ObjectMapper objectMapper;

    @Autowired
    public AccountUsecaseImpl(AccountService accountService, ObjectMapper objectMapper) {
        this.accountService = accountService;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<AccountDto> findAllUserAccount() {
        return objectMapper.mapList(accountService.findAllUserAccount(), AccountDto.class);
    }

    @Override
    public boolean updateAccountState(Long id) {
        return accountService.updateAccountState(id);
    }
}
