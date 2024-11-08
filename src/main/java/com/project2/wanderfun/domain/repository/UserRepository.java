package com.project2.wanderfun.domain.repository;

import com.project2.wanderfun.domain.model.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long>{
    Optional<User> findByEmail(String email);
}
