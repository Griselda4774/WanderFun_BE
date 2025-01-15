package com.project2.wanderfun.presentation.controller;

import com.project2.wanderfun.application.dto.ResponseDto;
import com.project2.wanderfun.application.dto.user.*;
import com.project2.wanderfun.application.usecase.UserUsecase;
import com.project2.wanderfun.presentation.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("wanderfun/user")
public class UserController {
    private final UserUsecase userUsecase;

    @Autowired
    public UserController(UserUsecase userUsecase) {
        this.userUsecase = userUsecase;
    }

    @GetMapping("")
    public ResponseEntity<ResponseDto<List<UserResponseDto>>> findAllUsers() {
        List<UserResponseDto> result = userUsecase.findAllUsers();
        if(result == null) {
            throw new RequestFailedException("Find all users failed!");
        }

        ResponseDto<List<UserResponseDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all users successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<UserResponseDto>> findUserById(@PathVariable long id) {
        UserResponseDto result = userUsecase.findUserById(id);
        if (result == null) {
            throw new RequestFailedException("Find user failed!");
        }

        ResponseDto<UserResponseDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find user successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseDto<UserResponseDto>> findUserByEmail(@PathVariable String email) {
        UserResponseDto result = userUsecase.findUserByEmail(email);
        if (result == null) {
            throw new RequestFailedException("Find user failed!");
        }

        ResponseDto<UserResponseDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find user successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto<UserResponseDto>> createUser(@RequestBody @Validated UserCreateDto userCreateDto) {
        boolean result = userUsecase.createUser(userCreateDto);
        if (!result) {
            throw new RequestFailedException("Create user failed!");
        }

        ResponseDto<UserResponseDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.CREATED.toString());
        response.setMessage("Create user successful!");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<UserResponseDto>> updateUserById(@PathVariable long id, @RequestBody @Validated UserUpdateDto userUpdateDto) {
        boolean result = userUsecase.updateUserById(id, userUpdateDto);
        if (!result) {
            throw new RequestFailedException("Update user failed!");
        }

        ResponseDto<UserResponseDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Update user successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/")
    public ResponseEntity<ResponseDto<UserResponseDto>> deleteAllUsers() {
        boolean result = userUsecase.deleteAllUsers();
        if (!result) {
            throw new RequestFailedException("Delete all users failed!");
        }

        ResponseDto<UserResponseDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete all users successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<UserResponseDto>> deleteUserById(@PathVariable long id) {
        boolean result = userUsecase.deleteUserById(id);
        if (!result) {
            throw new RequestFailedException("Delete user failed!");
        }

        ResponseDto<UserResponseDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete user successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/self")
    public ResponseEntity<ResponseDto<SelfInfoDto>> getSelfInfo(@RequestHeader("Authorization") String accessToken) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        SelfInfoDto result = userUsecase.getSelfInfo(accessToken);
        if (result == null) {
            throw new RequestFailedException("Get self info failed!");
        }

        ResponseDto<SelfInfoDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Get self info successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/self")
    public ResponseEntity<ResponseDto<SelfInfoDto>> updateSelfInfo(@RequestHeader("Authorization") String accessToken,
                                                         @RequestBody @Validated ChangeInfoDto changeInfoDto) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        boolean result = userUsecase.updateSelfInfo(accessToken, changeInfoDto);
        if (!result) {
            throw new RequestFailedException("Update self info failed!");
        }

        ResponseDto<SelfInfoDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Update self info successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("self")
    public ResponseEntity<ResponseDto<SelfInfoDto>> deleteSelf(@RequestHeader("Authorization") String accessToken) {
        boolean result = userUsecase.deleteSelf(accessToken);
        if (!result) {
            throw new RequestFailedException("Delete self failed!");
        }

        ResponseDto<SelfInfoDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete self successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
