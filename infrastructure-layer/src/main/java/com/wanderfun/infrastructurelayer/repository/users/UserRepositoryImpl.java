package com.wanderfun.infrastructurelayer.repository.users;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.repository.users.UserRepository;
import com.wanderfun.domainlayer.model.users.User;
import com.wanderfun.infrastructurelayer.persistence.entity.addresses.AddressEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.auths.AccountEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.images.ImageEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.users.UserEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.users.JpaUserRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public Optional<User> findByAccountId(Long accountId) {
        return jpaUserRepository.findByAccountId(accountId)
                .map(userEntity -> objectMapper.map(userEntity, User.class));
    }

    @Override
    public Long count() {
        return (long) jpaUserRepository.findAll().size();
    }
}
