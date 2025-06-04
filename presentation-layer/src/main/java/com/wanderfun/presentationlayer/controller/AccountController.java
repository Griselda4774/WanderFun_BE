package com.wanderfun.presentationlayer.controller;

import com.wanderfun.applicationlayer.dto.ResponseDto;
import com.wanderfun.applicationlayer.dto.auths.AccountDto;
import com.wanderfun.applicationlayer.usecase.AccountUsecase;
import com.wanderfun.presentationlayer.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("wanderfun/account")
public class AccountController {
    private final AccountUsecase accountUsecase;

    @Autowired
    public AccountController(AccountUsecase accountUsecase) {
        this.accountUsecase = accountUsecase;
    }

    @GetMapping("")
    public ResponseEntity<ResponseDto<List<AccountDto>>> findAllUserAccounts() {
        List<AccountDto> result = accountUsecase.findAllUserAccount();

        if (result.isEmpty()) {
            throw new RequestFailedException("Find accounts failed!");
        }

        ResponseDto<List<AccountDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all accounts successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/active/{id}")
    public ResponseEntity<ResponseDto<Boolean>> updateAccountState(@PathVariable Long id) {
        boolean result = accountUsecase.updateAccountState(id);

        if (!result) {
            throw new RequestFailedException("Update account state failed!");
        }

        ResponseDto<Boolean> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Update account state successful!");
        response.setData(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
