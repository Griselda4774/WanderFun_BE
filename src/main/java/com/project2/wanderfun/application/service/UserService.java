package com.project2.wanderfun.application.service;

import com.project2.wanderfun.domain.model.users.User;
import com.project2.wanderfun.domain.model.users.UserRole;

import java.util.List;

public interface UserService extends BaseService<User> {
    User findByEmail(String email);
    List<User> findAllByRole(UserRole role);
    List<User> findByOrderByPointDesc();
}
