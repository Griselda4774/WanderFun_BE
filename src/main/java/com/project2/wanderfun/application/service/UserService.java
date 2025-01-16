package com.project2.wanderfun.application.service;

import com.project2.wanderfun.domain.model.User;
import com.project2.wanderfun.domain.model.enums.UserRole;

import java.util.List;

public interface UserService extends BaseService<User> {
    User findByEmail(String email);
    List<User> findAllByRole(UserRole role);
    List<User> findByOrderByPointDesc();
}
