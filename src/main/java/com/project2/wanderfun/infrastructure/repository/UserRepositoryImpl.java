package com.project2.wanderfun.infrastructure.repository;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.UserRepository;
import com.project2.wanderfun.domain.model.User;
import com.project2.wanderfun.infrastructure.persistence.entity.UserEntity;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User, UserEntity, Long> implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Autowired
    public UserRepositoryImpl(UserJpaRepository userJpaRepository, ObjectMapper objectMapper) {
        super(userJpaRepository, objectMapper, User.class, UserEntity.class);
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(entity -> (User) objectMapper.map(entity, User.class));
    }
}
