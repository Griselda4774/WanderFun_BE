package com.project2.wanderfun.presentation.controller;

import com.project2.wanderfun.application.dto.UserDto;
import com.project2.wanderfun.application.usecase.UserUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wanderfun/user")
public class UserController {
    private final UserUsecase userUsecase;

    @Autowired
    public UserController(UserUsecase userUsecase) {
        this.userUsecase = userUsecase;
    }

    @GetMapping("/findUserByEmail/{email}")
    public ResponseEntity<UserDto> findUserByEmail(@PathVariable String email) {
        UserDto userDto = userUsecase.findUserByEmail(email);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/findUserById/{id}")
    public ResponseEntity<UserDto> findUserByEmail(@PathVariable long id) {
        UserDto userDto = userUsecase.findUserById(id);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(userDto);
    }
}
