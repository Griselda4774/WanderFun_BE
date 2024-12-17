package com.project2.wanderfun.application.service;

import com.project2.wanderfun.domain.model.User;
import com.project2.wanderfun.domain.model.enums.UserRole;

import java.util.List;

public interface UserService extends BaseService<User> {
    public User findByEmail(String email);
    public List<User> findByRole(UserRole role);
}
