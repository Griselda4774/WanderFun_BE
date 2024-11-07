package com.project2.wanderfun.adapter.controller;

import com.project2.wanderfun.application.dto.AccountDTO;
import com.project2.wanderfun.application.usecase.AuthenUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("wanderfun/auth")
public class AuthController {
    private final AuthenUsecase authenUsecase;

    @Autowired
    public AuthController(AuthenUsecase authenUsecase) {
        this.authenUsecase = authenUsecase;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail(email);
        accountDTO.setPassword(password);
        String result = authenUsecase.register(accountDTO);
        if (result.equals("Register successfully!")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
