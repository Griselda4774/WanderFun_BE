package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.applicationlayer.dto.auths.AccountDto;

import java.util.List;

public interface AccountUsecase {
    List<AccountDto> findAllUserAccount();
    boolean updateAccountState(Long id);

}
