package com.project2.wanderfun.application.service;

import com.project2.wanderfun.domain.model.User;

public interface UserService {
    public User findUserById(Long id);
    public User findUserByEmail(String email);
    public void createUser(User user);
    public void updateUserById(Long id, User user);
    public void deleteUserById(Long id);
}
