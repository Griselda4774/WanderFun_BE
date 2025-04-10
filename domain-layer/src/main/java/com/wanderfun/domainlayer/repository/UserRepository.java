package com.wanderfun.domainlayer.repository;

import com.wanderfun.domainlayer.model.users.User;
import com.wanderfun.domainlayer.model.users.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long>{
    Optional<User> findByEmail(String email);
    List<User> findAllByRole(UserRole role);
    List<User> findByOrderByPointDesc();
}
