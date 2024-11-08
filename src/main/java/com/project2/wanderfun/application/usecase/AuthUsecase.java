package com.project2.wanderfun.application.usecase;

import com.project2.wanderfun.application.dto.AccountDto;
import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.domain.service.UserService;
import com.project2.wanderfun.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthUsecase {
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public AuthUsecase(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    public String register(AccountDto accountDto) {
        User user = objectMapper.map(accountDto, User.class);
        try {
            try {
                User existingUser = userService.findUserByEmail(user.getEmail());
                return "User already exists";
            } catch (Exception e) {
                userService.createUser(user);
                return "Register successfully!";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
