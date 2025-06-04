package com.wanderfun.infrastructurelayer.usecase;

import com.wanderfun.applicationlayer.dto.users.MiniUserDto;
import com.wanderfun.applicationlayer.dto.users.UserCreateDto;
import com.wanderfun.applicationlayer.dto.users.UserDto;
import com.wanderfun.applicationlayer.exception.ObjectInvalidException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.users.UserService;
import com.wanderfun.applicationlayer.usecase.UserUsecase;
import com.wanderfun.applicationlayer.util.JwtUtil;
import com.wanderfun.domainlayer.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUsecaseImpl implements UserUsecase {
    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserUsecaseImpl(UserService userService, ObjectMapper objectMapper, JwtUtil jwtUtil) {
        this.userService = userService;
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
    }

//    public List<UserResponseDto> findAllUsers() {
//        return objectMapper.mapList(userService.findAllByRole(UserRole.USER), UserResponseDto.class);
//    }
//
//    public UserResponseDto findUserById(Long id) {
//        return objectMapper.map(userService.findById(id), UserResponseDto.class);
//    }
//
//    public UserResponseDto findUserByEmail(String email) {
//        return objectMapper.map(userService.findByEmail(email), UserResponseDto.class);
//    }
//
//    public boolean createUser(UserCreateDto userCreateDto) throws ObjectAlreadyExistException {
//        User user = objectMapper.map(userCreateDto, User.class);
//        User existingUser = null;
//        try {
//            existingUser = userService.findByEmail(user.getEmail());
//        } catch (Exception e) {
//        }
//
//        if (existingUser != null) {
//            throw new ObjectAlreadyExistException(String.format("Email already used"));
//        }
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userService.create(user);
//        return true;
//    }
//
//    public boolean updateUserById(Long id, UserUpdateDto userUpdateDto) throws ObjectAlreadyExistException {
//        User user = objectMapper.map(userUpdateDto, User.class);
//        User currentUser = userService.findById(id);
//        if (!user.getEmail().equals(currentUser.getEmail())) {
//            User existingUser;
//            try {
//                existingUser = userService.findByEmail(user.getEmail());
//            } catch (Exception e) {
//                existingUser = null;
//            }
//            if (existingUser != null) {
//                throw new ObjectAlreadyExistException("This email is already used!");
//            }
//        }
//
//        userService.updateById(id, user);
//        return true;
//    }
//
//    public boolean deleteUserById(Long id) {
//        userService.deleteById(id);
//        return true;
//    }
//
//    public boolean deleteAllUsers() {
//        userService.deleteAll();
//        return true;
//    }

//    public SelfInfoDto getSelfInfo(String accessToken) {
//        return objectMapper.map(userService.findById(jwtUtil.getIdFromToken(accessToken)), SelfInfoDto.class);
//    }
//
//    public boolean updateSelfInfo(String accessToken, ChangeInfoDto changeInfoDto) {
//        User user = objectMapper.map(changeInfoDto, User.class);
//        userService.updateById(jwtUtil.getIdFromToken(accessToken), user);
//        return true;
//    }
//
//    public boolean deleteSelf(String accessToken) {
//        userService.deleteById(jwtUtil.getIdFromToken(accessToken));
//        return true;
//    }

    @Override
    public MiniUserDto getMiniSelfInfo(String assessToken) {
        if (!jwtUtil.validateToken(assessToken)) {
            throw new ObjectInvalidException("Invalid access token");
        }
        return objectMapper.map(userService.findByAccountId(jwtUtil.getIdFromToken(assessToken)), MiniUserDto.class);
    }

    @Override
    public UserDto getSelfInfo(String assessToken) {
        if (!jwtUtil.validateToken(assessToken)) {
            throw new ObjectInvalidException("Invalid access token");
        }
        return objectMapper.map(userService.findByAccountId(jwtUtil.getIdFromToken(assessToken)), UserDto.class);
    }

    @Override
    public UserDto updateSelfInfo(String assessToken, UserCreateDto userCreateDto) {
        if (!jwtUtil.validateToken(assessToken)) {
            throw new ObjectInvalidException("Invalid access token");
        }
        Long userId = userService.findByAccountId(jwtUtil.getIdFromToken(assessToken)).getId();
        User user = objectMapper.map(userCreateDto, User.class);
        user.setAccountId(jwtUtil.getIdFromToken(assessToken));
        return objectMapper.map(userService.updateById(userId, user), UserDto.class);
    }
}
