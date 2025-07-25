package com.wanderfun.presentationlayer.controller;

import com.wanderfun.applicationlayer.dto.ResponseDto;
import com.wanderfun.applicationlayer.dto.auths.*;
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

    @GetMapping("/otp")
    public ResponseEntity<ResponseDto<Void>> sendOtp(@RequestParam String email) {
        boolean result = authUsecase.sendOtp(email);
        if (!result) {
            throw new RequestFailedException("Failed to send OTP!");
        }

        ResponseDto<Void> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Send OTP successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/otp/verify")
    public ResponseEntity<ResponseDto<Void>> verifyOtp(@RequestBody @Validated MailOtpDto mailOtpDto) {
        boolean result = authUsecase.verifyOtp(mailOtpDto);
        if (!result) {
            throw new RequestFailedException("Verify OTP failed!");
        }

        ResponseDto<Void> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Verify OTP successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/password/forgot")
    public ResponseEntity<ResponseDto<Void>> forgotPassword(@RequestBody @Validated ForgotPasswordDto forgotPasswordDto) {
        boolean result = authUsecase.forgotPassword(forgotPasswordDto);
        if (!result) {
            throw new RequestFailedException("Forgot password failed!");
        }

        ResponseDto<Void> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Forgot password successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/password/change")
    public ResponseEntity<ResponseDto<Void>> changePassword(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody @Validated ChangePasswordDto changePasswordDto) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        boolean result = authUsecase.changePassword(accessToken, changePasswordDto);
        if (!result) {
            throw new RequestFailedException("Change password failed!");
        }

        ResponseDto<Void> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Change password successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
