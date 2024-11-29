package com.project2.wanderfun.application.usecase;

import com.project2.wanderfun.application.dto.UserDto;
import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserUsecase {
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserUsecase(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    public List<UserDto> findAllUsers() {
        return objectMapper.mapList(userService.findAll(), UserDto.class);
    }

    public UserDto findUserById(Long id) {
        return objectMapper.map(userService.findById(id), UserDto.class);
    }

    public UserDto findUserByEmail(String email) {
        return objectMapper.map(userService.findByEmail(email), UserDto.class);
    }
}
