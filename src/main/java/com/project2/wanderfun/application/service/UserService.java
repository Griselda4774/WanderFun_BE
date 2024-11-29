package com.project2.wanderfun.application.service;

import com.project2.wanderfun.domain.model.User;

public interface UserService extends BaseService<User> {
    public User findByEmail(String email);
}
