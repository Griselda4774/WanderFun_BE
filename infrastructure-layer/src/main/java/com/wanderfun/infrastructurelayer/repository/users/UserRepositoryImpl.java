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

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User, UserEntity, Long> implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    @Autowired
    public UserRepositoryImpl(JpaUserRepository jpaUserRepository, ObjectMapper objectMapper) {
        super(jpaUserRepository, objectMapper, User.class, UserEntity.class);
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = objectMapper.map(user, UserEntity.class);
        mapEntityRelation(user, userEntity);
        UserEntity savedUserEntity = jpaUserRepository.save(userEntity);
        return objectMapper.map(savedUserEntity, User.class);
    }

    private void mapEntityRelation(User user, UserEntity userEntity) {
        if (user.getAccountId() != null) {
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setId(user.getAccountId());
            userEntity.setAccount(accountEntity);
        }

        if (user.getAvatarImage() != null && user.getAvatarImage().getId() != null) {
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setId(user.getAvatarImage().getId());
            userEntity.setAvatarImage(imageEntity);
        }

        if (user.getAddress() != null && user.getAddress().getId() != null) {
            AddressEntity addressEntity = new AddressEntity();
            addressEntity.setId(user.getAddress().getId());
            userEntity.setAddress(addressEntity);
        }
    }
}
