package com.wanderfun.applicationlayer.service;

import com.wanderfun.domainlayer.model.users.User;
import com.wanderfun.domainlayer.model.users.UserRole;

import java.util.List;

public interface UserService extends BaseService<User> {
    User findByEmail(String email);
    List<User> findAllByRole(UserRole role);
    List<User> findByOrderByPointDesc();
}
