package com.project2.wanderfun.infrastructure.repository;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.UserRepository;
import com.project2.wanderfun.domain.model.User;
import com.project2.wanderfun.domain.model.enums.UserRole;
import com.project2.wanderfun.infrastructure.persistence.entity.UserEntity;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User, UserEntity, Long> implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    @Autowired
    public UserRepositoryImpl(JpaUserRepository jpaUserRepository, ObjectMapper objectMapper) {
        super(jpaUserRepository, objectMapper, User.class, UserEntity.class);
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<User> findByEmail (String email) {
        return jpaUserRepository.findByEmail(email)
                    .map(entity -> objectMapper.map(entity, User.class));
    }

    @Override
    public List<User> findAllByRole(UserRole role) {
        return objectMapper.mapList(jpaUserRepository.findAllByRole(role.name()), User.class);
    }

    @Override
    public List<User> findByOrderByPointDesc() {
        return objectMapper.mapList(jpaUserRepository.findByRoleOrderByPointDesc(UserRole.USER.name()), User.class);
    }
}
