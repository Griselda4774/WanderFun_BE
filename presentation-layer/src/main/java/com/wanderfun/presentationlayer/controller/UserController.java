package com.wanderfun.presentationlayer.controller;

import com.wanderfun.applicationlayer.dto.ResponseDto;
import com.wanderfun.applicationlayer.dto.users.MiniUserDto;
import com.wanderfun.applicationlayer.dto.users.UserCreateDto;
import com.wanderfun.applicationlayer.dto.users.UserDto;
import com.wanderfun.applicationlayer.usecase.UserUsecase;
import com.wanderfun.presentationlayer.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("wanderfun/user")
public class UserController {
    private final UserUsecase userUsecase;

    @Autowired
    public UserController(UserUsecase userUsecase) {
        this.userUsecase = userUsecase;
    }

//    @GetMapping("")
//    public ResponseEntity<ResponseDto<List<UserResponseDto>>> findAllUsers() {
//        List<UserResponseDto> result = userUsecase.findAllUsers();
//        if(result == null) {
//            throw new RequestFailedException("Find all users failed!");
//        }
//
//        ResponseDto<List<UserResponseDto>> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Find all users successful!");
//        response.setData(result);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ResponseDto<UserResponseDto>> findUserById(@PathVariable long id) {
//        UserResponseDto result = userUsecase.findUserById(id);
//        if (result == null) {
//            throw new RequestFailedException("Find user failed!");
//        }
//
//        ResponseDto<UserResponseDto> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Find user successful!");
//        response.setData(result);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @GetMapping("/email/{email}")
//    public ResponseEntity<ResponseDto<UserResponseDto>> findUserByEmail(@PathVariable String email) {
//        UserResponseDto result = userUsecase.findUserByEmail(email);
//        if (result == null) {
//            throw new RequestFailedException("Find user failed!");
//        }
//
//        ResponseDto<UserResponseDto> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Find user successful!");
//        response.setData(result);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @PostMapping("")
//    public ResponseEntity<ResponseDto<UserResponseDto>> createUser(@RequestBody @Validated UserCreateDto userCreateDto) {
//        boolean result = userUsecase.createUser(userCreateDto);
//        if (!result) {
//            throw new RequestFailedException("Create user failed!");
//        }
//
//        ResponseDto<UserResponseDto> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.CREATED.toString());
//        response.setMessage("Create user successful!");
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ResponseDto<UserResponseDto>> updateUserById(@PathVariable long id, @RequestBody @Validated UserUpdateDto userUpdateDto) {
//        boolean result = userUsecase.updateUserById(id, userUpdateDto);
//        if (!result) {
//            throw new RequestFailedException("Update user failed!");
//        }
//
//        ResponseDto<UserResponseDto> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Update user successful!");
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @DeleteMapping("/")
//    public ResponseEntity<ResponseDto<UserResponseDto>> deleteAllUsers() {
//        boolean result = userUsecase.deleteAllUsers();
//        if (!result) {
//            throw new RequestFailedException("Delete all users failed!");
//        }
//
//        ResponseDto<UserResponseDto> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Delete all users successful!");
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ResponseDto<UserResponseDto>> deleteUserById(@PathVariable long id) {
//        boolean result = userUsecase.deleteUserById(id);
//        if (!result) {
//            throw new RequestFailedException("Delete user failed!");
//        }
//
//        ResponseDto<UserResponseDto> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Delete user successful!");
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @GetMapping("/self")
//    public ResponseEntity<ResponseDto<SelfInfoDto>> getSelfInfo(@RequestHeader("Authorization") String accessToken) {
//        if (accessToken.startsWith("Bearer ")) {
//            accessToken = accessToken.substring(7);
//        }
//
//        SelfInfoDto result = userUsecase.getSelfInfo(accessToken);
//        if (result == null) {
//            throw new RequestFailedException("Get self info failed!");
//        }
//
//        ResponseDto<SelfInfoDto> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Get self info successful!");
//        response.setData(result);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }

    @GetMapping("/self/mini")
    public ResponseEntity<ResponseDto<MiniUserDto>> getMiniSelfInfo(@RequestHeader("Authorization") String accessToken) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        MiniUserDto result = userUsecase.getMiniSelfInfo(accessToken);
        if (result == null) {
            throw new RequestFailedException("Get mini self info failed!");
        }

        ResponseDto<MiniUserDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Get mini self info successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/self")
    public ResponseEntity<ResponseDto<UserDto>> getSelfInfo(@RequestHeader("Authorization") String accessToken) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        UserDto result = userUsecase.getSelfInfo(accessToken);
        if (result == null) {
            throw new RequestFailedException("Get self info failed!");
        }

        ResponseDto<UserDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Get self info successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/self")
    public ResponseEntity<ResponseDto<UserDto>> updateSelfInfo(@RequestHeader("Authorization") String accessToken,
                                                               @RequestBody UserCreateDto userCreateDto) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        UserDto result = userUsecase.updateSelfInfo(accessToken, userCreateDto);
        if (result == null) {
            throw new RequestFailedException("Update self info failed!");
        }

        ResponseDto<UserDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Update self info successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
//
//    @DeleteMapping("self")
//    public ResponseEntity<ResponseDto<SelfInfoDto>> deleteSelf(@RequestHeader("Authorization") String accessToken) {
//        boolean result = userUsecase.deleteSelf(accessToken);
//        if (!result) {
//            throw new RequestFailedException("Delete self failed!");
//        }
//
//        ResponseDto<SelfInfoDto> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Delete self successful!");
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
}
