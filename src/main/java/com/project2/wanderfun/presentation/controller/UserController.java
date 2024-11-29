package com.project2.wanderfun.presentation.controller;

import com.project2.wanderfun.application.dto.ResponseDto;
import com.project2.wanderfun.application.dto.UserDto;
import com.project2.wanderfun.application.usecase.UserUsecase;
import com.project2.wanderfun.presentation.exception.ObjectNotFoundException;
import com.project2.wanderfun.presentation.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wanderfun/user")
public class UserController {
    private final UserUsecase userUsecase;

    @Autowired
    public UserController(UserUsecase userUsecase) {
        this.userUsecase = userUsecase;
    }

    @GetMapping("")
    public ResponseEntity<ResponseDto<List<UserDto>>> findAllUsers() {
        List<UserDto> result = userUsecase.findAllUsers();
        if(result == null) {
            throw new RequestFailedException("Find all users failed!");
        }

        ResponseDto<List<UserDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all users successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<UserDto>> findUserById(@PathVariable long id) {
        UserDto result = userUsecase.findUserById(id);
        if (result == null) {
            throw new RequestFailedException("Find user failed!");
        }

        ResponseDto<UserDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find user successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseDto<UserDto>> findUserByEmail(@PathVariable String email) {
        UserDto result = userUsecase.findUserByEmail(email);
        if (result == null) {
            throw new RequestFailedException("Find user failed!");
        }

        ResponseDto<UserDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find user successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto<?>> createUser(@RequestBody UserDto userDto) {
        boolean result = userUsecase.createUser(userDto);
        if (result != true) {
            throw new RequestFailedException("Create user failed!");
        }

        ResponseDto<?> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.CREATED.toString());
        response.setMessage("Create user successful!");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<?>> updateUserById(@PathVariable long id, @RequestBody UserDto userDto) {
        boolean result = userUsecase.updateUserById(id, userDto);
        if (result != true) {
            throw new RequestFailedException("Update user failed!");
        }

        ResponseDto<?> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Update user successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/")
    public ResponseEntity<ResponseDto<?>> deleteAllUsers() {
        boolean result = userUsecase.deleteAllUsers();
        if (result != true) {
            throw new RequestFailedException("Delete all users failed!");
        }

        ResponseDto<?> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete all users successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<?>> deleteUserById(@PathVariable long id) {
        boolean result = userUsecase.deleteUserById(id);
        if (result != true) {
            throw new RequestFailedException("Delete user failed!");
        }

        ResponseDto<?> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete user successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
