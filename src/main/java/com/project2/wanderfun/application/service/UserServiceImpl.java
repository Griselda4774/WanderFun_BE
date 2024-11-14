package com.project2.wanderfun.application.service;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.domain.repository.UserRepository;
import com.project2.wanderfun.domain.model.User;
import com.project2.wanderfun.domain.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ObjectMapper objectMapper, UserRepository userRepository) {
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    @Transactional
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void createUser(User user) {
        user.setId(null);
        userRepository.save(user);
    }

    @Override
    public void updateUserById(Long id, User user) {
        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
