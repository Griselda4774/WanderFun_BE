package com.project2.wanderfun.application.service.impl;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.domain.model.User;
import com.project2.wanderfun.application.service.UserService;
import com.project2.wanderfun.infrastructure.persistence.entity.UserEntity;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final ObjectMapper<?> objectMapper;
    private final UserJpaRepository userJpaRepository;

    @Autowired
    public UserServiceImpl(ObjectMapper<?> objectMapper, UserJpaRepository userJpaRepository) {
        this.objectMapper = objectMapper;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User getUserById(Long id) {
        return objectMapper.map(userJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")), User.class);
    }

    @Override
    public User getUserByEmail(String email) {
        return objectMapper.map(userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found")), User.class);
    }

    @Override
    public void createUser(User user) {
        user.setId(null);
        userJpaRepository.save(objectMapper.map(user, UserEntity.class));
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
