package com.project2.wanderfun.application.repository;

import com.project2.wanderfun.domain.model.users.User;
import com.project2.wanderfun.domain.model.users.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long>{
    Optional<User> findByEmail(String email);
    List<User> findAllByRole(UserRole role);
    List<User> findByOrderByPointDesc();
}
