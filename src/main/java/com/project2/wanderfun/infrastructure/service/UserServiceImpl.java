package com.project2.wanderfun.infrastructure.service;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.UserRepository;
import com.project2.wanderfun.domain.model.User;
import com.project2.wanderfun.application.service.UserService;
import com.project2.wanderfun.application.exception.ObjectNotFoundException;
import com.project2.wanderfun.domain.model.enums.UserRole;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ObjectMapper objectMapper) {
        super(userRepository, objectMapper, User.class);
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", User.class.getSimpleName())));
    }

    @Override
    @Transactional
    public List<User> findAllByRole(UserRole role) {
        return userRepository.findAllByRole(role);
    }
}
