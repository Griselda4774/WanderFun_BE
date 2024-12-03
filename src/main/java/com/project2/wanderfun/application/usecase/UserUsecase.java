package com.project2.wanderfun.application.usecase;

import com.project2.wanderfun.application.dto.user.SelfInfoDto;
import com.project2.wanderfun.application.dto.user.UserCreateDto;
import com.project2.wanderfun.application.dto.user.UserResponseDto;
import com.project2.wanderfun.application.dto.user.UserUpdateDto;
import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.service.UserService;
import com.project2.wanderfun.application.util.JwtUtil;
import com.project2.wanderfun.domain.model.User;
import com.project2.wanderfun.application.exception.NotHavePermissionException;
import com.project2.wanderfun.application.exception.ObjectAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserUsecase {
    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserUsecase(UserService userService, ObjectMapper objectMapper, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponseDto> findAllUsers() {
        return objectMapper.mapList(userService.findAll(), UserResponseDto.class);
    }

    public UserResponseDto findUserById(Long id) {
        return objectMapper.map(userService.findById(id), UserResponseDto.class);
    }

    public UserResponseDto findUserByEmail(String email) {
        return objectMapper.map(userService.findByEmail(email), UserResponseDto.class);
    }

    public SelfInfoDto getSelfInfoById(Long id, String accessToken) {
        if (id != jwtUtil.getIdFromToken(accessToken)) {
            throw new NotHavePermissionException("You don't have permission!");
        }

        return objectMapper.map(userService.findById(id), SelfInfoDto.class);
    }

    public SelfInfoDto getSelfInfoByEmail(String email, String accessToken) {
        if (!email.equals(jwtUtil.getEmailFromToken(accessToken))) {
            throw new NotHavePermissionException("You don't have permission!");
        }

        return objectMapper.map(userService.findByEmail(email), SelfInfoDto.class);
    }

    public boolean createUser(UserCreateDto userCreateDto) {
        User user = objectMapper.map(userCreateDto, User.class);
        User existingUser = null;
        try {
            existingUser = userService.findByEmail(user.getEmail());
        } catch (Exception e) {
        }

        if (existingUser != null) {
            throw new ObjectAlreadyExistException(String.format("Email already used"));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.create(user);
        return true;
    }

    public boolean updateUserById(Long id, UserUpdateDto userUpdateDto) {
        User user = objectMapper.map(userUpdateDto, User.class);
        User existingUser = null;
        try {
            existingUser = userService.findByEmail(user.getEmail());
        } catch (Exception e) {
        }

        if (existingUser != null) {
            throw new ObjectAlreadyExistException(String.format("Email already used"));
        }

        userService.updateById(id, user);
        return true;
    }

    public boolean deleteUserById(Long id) {
        userService.deleteById(id);
        return true;
    }

    public boolean deleteAllUsers() {
        userService.deleteAll();
        return true;
    }
}
