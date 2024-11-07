package com.project2.wanderfun.infrastructure.persistence.jpaRepository;

import com.project2.wanderfun.infrastructure.persistence.entity.UserEntity;

import java.util.Optional;

public interface UserJpaRepository extends BaseJpaRepository<UserEntity, Long>{
    Optional<UserEntity> findByEmail(String email);
}
