package com.project2.wanderfun.presentation.controller;

import com.project2.wanderfun.application.dto.AccountDto;
import com.project2.wanderfun.application.dto.LoginResponseDto;
import com.project2.wanderfun.application.dto.ResponseDto;
import com.project2.wanderfun.application.dto.TokenResponseDto;
import com.project2.wanderfun.application.usecase.AuthUsecase;
import com.project2.wanderfun.presentation.exception.RequestFailedException;
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
    public ResponseEntity<ResponseDto<?>> register(@RequestBody @Validated AccountDto accountDto)
    throws RequestFailedException {
        String result = authUsecase.register(accountDto);

        ResponseDto<?> responseDto = new ResponseDto<>();

        if (!result.equals("Register successfully!")) {
            throw new RequestFailedException("Register failed!");
        }
        responseDto.setStatusCode(HttpStatus.OK.toString());
        responseDto.setMessage(result);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<ResponseDto<?>> registerAdmin(@RequestBody @Validated AccountDto accountDto)
    throws RequestFailedException {
        String result = authUsecase.registerAdmin(accountDto);

        ResponseDto<?> responseDto = new ResponseDto<>();

        if (!result.equals("Register admin successfully!")) {
            throw new RequestFailedException("Register admin failed!");
        }

        responseDto.setStatusCode(HttpStatus.OK.toString());
        responseDto.setMessage(result);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody @Validated AccountDto accountDto)
    throws RequestFailedException {
        LoginResponseDto result = authUsecase.login(accountDto);
        ResponseDto<LoginResponseDto> response = new ResponseDto<>();

        if (result == null) {
            throw new RequestFailedException("Login failed!");
        }

        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Login successfully!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/logout")
    public ResponseEntity<ResponseDto> logout(@RequestHeader("Authorization") String refreshToken)
    throws RequestFailedException {
        if (refreshToken.startsWith("Bearer ")) {
            refreshToken = refreshToken.substring(7);
        }

        String result = authUsecase.logout(refreshToken);
        ResponseDto response = new ResponseDto();

        if (!result.equals("Logout successfully!")) {
            throw new RequestFailedException("Logout failed!");
        }

        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Logout successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
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
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
