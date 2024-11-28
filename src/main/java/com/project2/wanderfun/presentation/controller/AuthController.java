package com.project2.wanderfun.presentation.controller;

import com.project2.wanderfun.application.dto.AccountDto;
import com.project2.wanderfun.application.dto.LoginResponseDto;
import com.project2.wanderfun.application.dto.ResponseDto;
import com.project2.wanderfun.application.dto.TokenResponseDto;
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

    @GetMapping("/logout")
    public ResponseEntity<ResponseDto> logout(@RequestHeader("Authorization") String refreshToken) {
        if (refreshToken.startsWith("Bearer ")) {
            refreshToken = refreshToken.substring(7);
        }

        String result = authUsecase.logout(refreshToken);
        if (result.equals("Logout successfully!")) {
            ResponseDto response = new ResponseDto();
            response.setStatusCode(HttpStatus.OK.toString());
            response.setMessage("Logout successfully!");
            response.setTimestamp(new Date());
            response.setData(null);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        ResponseDto response = new ResponseDto();
        response.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        response.setMessage("Logout failed!");
        response.setTimestamp(new Date());
        response.setData(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("/refresh")
    public ResponseEntity<ResponseDto> refresh(@RequestHeader("Authorization") String refreshToken) {
        if (refreshToken.startsWith("Bearer ")) {
            refreshToken = refreshToken.substring(7);
        }

        TokenResponseDto result = authUsecase.refresh(refreshToken);
        ResponseDto<TokenResponseDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Refresh token successfully!");
        response.setTimestamp(new Date());
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
