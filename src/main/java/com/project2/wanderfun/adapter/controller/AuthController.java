package com.project2.wanderfun.adapter.controller;

import com.project2.wanderfun.application.dto.AccountDto;
import com.project2.wanderfun.application.usecase.AuthUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wanderfun/auth")
public class AuthController {
    private final AuthUsecase authUsecase;

    @Autowired
    public AuthController(AuthUsecase authUsecase) {
        this.authUsecase = authUsecase;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Validated AccountDto accountDto) {
        String result = authUsecase.register(accountDto);
        if (result.equals("Register successfully!")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
