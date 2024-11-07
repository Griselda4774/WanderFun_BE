package com.project2.wanderfun.application.usecase;

import com.project2.wanderfun.application.dto.AccountDTO;
import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.UserRepository;
import com.project2.wanderfun.application.service.UserService;
import com.project2.wanderfun.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenUsecase {
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public AuthenUsecase(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    public String register(AccountDTO accountDTO) {
        User user = objectMapper.map(accountDTO, User.class);
        try {
            userService.createUser(user);
            return "User created successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
