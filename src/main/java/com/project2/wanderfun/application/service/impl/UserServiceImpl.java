package com.project2.wanderfun.application.service.impl;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.UserRepository;
import com.project2.wanderfun.domain.model.User;
import com.project2.wanderfun.application.service.UserService;
import com.project2.wanderfun.infrastructure.persistence.entity.UserEntity;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final ObjectMapper objectMapper;
    private final UserJpaRepository userJpaRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ObjectMapper objectMapper, UserJpaRepository userJpaRepository, UserRepository userRepository) {
        this.objectMapper = objectMapper;
        this.userJpaRepository = userJpaRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(Long id) {
        return objectMapper.map(userJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")), User.class);
    }

    @Override
    public User findUserByEmail(String email) {
        return objectMapper.map(userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found")), User.class);
    }

    @Override
    public void createUser(User user) {
        user.setId(null);
        userRepository.save(user);
    }

    @Override
    public void updateUserById(Long id, User user) {
        user.setId(id);
        userJpaRepository.save(objectMapper.map(user, UserEntity.class));
    }

    @Override
    public void deleteUserById(Long id) {
        userJpaRepository.deleteById(id);
    }
}