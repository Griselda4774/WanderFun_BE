package com.wanderfun.presentationlayer.controller;

import com.wanderfun.applicationlayer.dto.ResponseDto;
import com.wanderfun.applicationlayer.dto.auths.LoginDto;
import com.wanderfun.applicationlayer.dto.auths.LoginResponseDto;
import com.wanderfun.applicationlayer.dto.auths.RegisterDto;
import com.wanderfun.applicationlayer.dto.auths.TokenResponseDto;
import com.wanderfun.applicationlayer.usecase.AuthUsecase;
import com.wanderfun.presentationlayer.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("wanderfun/auth")
public class AuthController {
    private final AuthUsecase authUsecase;

    @Autowired
    public AuthController(AuthUsecase authUsecase) {
        this.authUsecase = authUsecase;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto<LoginResponseDto>> register(@RequestBody @Validated RegisterDto registerDto)
    throws RequestFailedException {
        boolean result = authUsecase.register(registerDto);
        if (result != true) {
            throw new RequestFailedException("Register failed!");
        }

        ResponseDto<LoginResponseDto> responseDto = new ResponseDto<>();
        responseDto.setStatusCode(HttpStatus.OK.toString());
        responseDto.setMessage("Register successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/admin/register")
    public ResponseEntity<ResponseDto<LoginResponseDto>> registerAdmin(@RequestBody @Validated RegisterDto registerDto)
    throws RequestFailedException {
        boolean result = authUsecase.registerAdmin(registerDto);
        if (!result) {
            throw new RequestFailedException("Register admin failed!");
        }

        ResponseDto<LoginResponseDto> responseDto = new ResponseDto<>();
        responseDto.setStatusCode(HttpStatus.OK.toString());
        responseDto.setMessage("Register admin successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<LoginResponseDto>> login(@RequestBody @Validated LoginDto loginDto)
    throws RequestFailedException {
        LoginResponseDto result = authUsecase.login(loginDto);
        if (result == null) {
            throw new RequestFailedException("Login failed!");
        }

        ResponseDto<LoginResponseDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Login successfully!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/logout")
    public ResponseEntity<ResponseDto<LoginResponseDto>> logout(@RequestHeader("Authorization") String accessToken)
    throws RequestFailedException {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        boolean result = authUsecase.logout(accessToken);
        if (!result) {
            throw new RequestFailedException("Logout failed!");
        }

        ResponseDto<LoginResponseDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Logout successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/refresh")
    public ResponseEntity<ResponseDto<TokenResponseDto>> refresh(@RequestHeader("Authorization") String refreshToken) {
        if (refreshToken.startsWith("Bearer ")) {
            refreshToken = refreshToken.substring(7);
        }

        TokenResponseDto result = authUsecase.refresh(refreshToken);
        if (result == null) {
            throw new RequestFailedException("Refresh token failed!");
        }

        ResponseDto<TokenResponseDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Refresh token successfully!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
