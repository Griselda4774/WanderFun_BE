//package com.wanderfun.infrastructurelayer.service;
//
//import com.wanderfun.applicationlayer.mapper.ObjectMapper;
//import com.wanderfun.domainlayer.repository.UserRepository;
//import com.wanderfun.domainlayer.model.users.User;
//import com.wanderfun.applicationlayer.service.UserService;
//import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
//import com.wanderfun.domainlayer.model.users.UserRole;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserServiceImpl(UserRepository userRepository, ObjectMapper objectMapper) {
//        super(userRepository, objectMapper, User.class);
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    @Transactional
//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", User.class.getSimpleName())));
//    }
//
//    @Override
//    @Transactional
//    public List<User> findAllByRole(UserRole role) {
//        return userRepository.findAllByRole(role);
//    }
//
//    @Override
//    @Transactional
//    public List<User> findByOrderByPointDesc() {
//        return userRepository.findByOrderByPointDesc();
//    }
//}
