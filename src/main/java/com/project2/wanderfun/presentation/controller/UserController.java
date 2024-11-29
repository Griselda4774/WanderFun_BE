package com.project2.wanderfun.presentation.controller;

import com.project2.wanderfun.application.dto.ResponseDto;
import com.project2.wanderfun.application.dto.UserDto;
import com.project2.wanderfun.application.usecase.UserUsecase;
import com.project2.wanderfun.presentation.exception.ObjectNotFoundException;
import com.project2.wanderfun.presentation.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<ResponseDto<List<UserDto>>> findAll() {
        List<UserDto> users = userUsecase.findAllUsers();
        ResponseDto<List<UserDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all users successful!");
        response.setData(users);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<UserDto>> findById(@PathVariable long id) {
        UserDto userDto = userUsecase.findUserById(id);
        ResponseDto<UserDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find user successful!");
        response.setData(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseDto<UserDto>> findByEmail(@PathVariable String email) {
        UserDto userDto = userUsecase.findUserByEmail(email);
        ResponseDto<UserDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find user successful!");
        response.setData(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
