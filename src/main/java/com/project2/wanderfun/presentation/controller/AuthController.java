package com.project2.wanderfun.presentation.controller;

import com.project2.wanderfun.application.dto.AccountDto;
import com.project2.wanderfun.application.dto.LoginResponseDto;
import com.project2.wanderfun.application.dto.ResponseDto;
import com.project2.wanderfun.application.usecase.AuthUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @PostMapping("/register/admin")
    public ResponseEntity<String> registerAdmin(@RequestBody @Validated AccountDto accountDto) {
        String result = authUsecase.registerAdmin(accountDto);
        if (result.equals("Register admin successfully!")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody @Validated AccountDto accountDto) {
        LoginResponseDto result = authUsecase.login(accountDto);
        ResponseDto<LoginResponseDto> response = new ResponseDto<>();
        if (result != null) {
            response.setStatusCode(HttpStatus.OK.toString());
            response.setMessage("Login successfully!");
            response.setTimestamp(new Date());
            response.setData(result);
            return ResponseEntity.ok(response);
        }

        response.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        response.setMessage("Login failed!");
        response.setTimestamp(new Date());
        response.setData(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
