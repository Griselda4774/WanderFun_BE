package com.project2.wanderfun.application.usecase;

import com.project2.wanderfun.application.dto.UserDto;
import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUsecase {
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserUsecase(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    public UserDto findUserByEmail(String email) {
        try {
            return objectMapper.map(userService.findUserByEmail(email), UserDto.class);
        } catch (Exception e) {
            return null;
        }
    }

    public UserDto findUserById(Long id) {
        return objectMapper.map(userService.findUserById(id), UserDto.class);
    }
}
