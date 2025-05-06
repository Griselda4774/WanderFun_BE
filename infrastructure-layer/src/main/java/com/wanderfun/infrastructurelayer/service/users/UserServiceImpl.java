package com.wanderfun.infrastructurelayer.service.users;

import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.repository.users.UserRepository;
import com.wanderfun.domainlayer.model.users.User;
import com.wanderfun.applicationlayer.service.users.UserService;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ObjectMapper objectMapper) {
        super(userRepository, objectMapper, User.class);
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User findByAccountId(Long accountId) {
        return userRepository.findByAccountId(accountId)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", User.class.getSimpleName())));
    }
}
