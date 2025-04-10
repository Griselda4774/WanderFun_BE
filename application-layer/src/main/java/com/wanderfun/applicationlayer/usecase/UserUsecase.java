package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.applicationlayer.dto.user.*;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.UserService;
import com.wanderfun.applicationlayer.util.JwtUtil;
import com.wanderfun.domainlayer.model.users.User;
import com.wanderfun.applicationlayer.exception.ObjectAlreadyExistException;
import com.wanderfun.domainlayer.model.users.UserRole;
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
        return objectMapper.mapList(userService.findAllByRole(UserRole.USER), UserResponseDto.class);
    }

    public UserResponseDto findUserById(Long id) {
        return objectMapper.map(userService.findById(id), UserResponseDto.class);
    }

    public UserResponseDto findUserByEmail(String email) {
        return objectMapper.map(userService.findByEmail(email), UserResponseDto.class);
    }

    public boolean createUser(UserCreateDto userCreateDto) throws ObjectAlreadyExistException {
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

    public boolean updateUserById(Long id, UserUpdateDto userUpdateDto) throws ObjectAlreadyExistException {
        User user = objectMapper.map(userUpdateDto, User.class);
        User currentUser = userService.findById(id);
        if (!user.getEmail().equals(currentUser.getEmail())) {
            User existingUser;
            try {
                existingUser = userService.findByEmail(user.getEmail());
            } catch (Exception e) {
                existingUser = null;
            }
            if (existingUser != null) {
                throw new ObjectAlreadyExistException("This email is already used!");
            }
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

    public SelfInfoDto getSelfInfo(String accessToken) {
        return objectMapper.map(userService.findById(jwtUtil.getIdFromToken(accessToken)), SelfInfoDto.class);
    }

    public boolean updateSelfInfo(String accessToken, ChangeInfoDto changeInfoDto) {
        User user = objectMapper.map(changeInfoDto, User.class);
        userService.updateById(jwtUtil.getIdFromToken(accessToken), user);
        return true;
    }

    public boolean deleteSelf(String accessToken) {
        userService.deleteById(jwtUtil.getIdFromToken(accessToken));
        return true;
    }
}
