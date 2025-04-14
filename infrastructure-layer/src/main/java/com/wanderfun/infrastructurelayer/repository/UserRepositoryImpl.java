//package com.wanderfun.infrastructurelayer.repository;
//
//import com.wanderfun.applicationlayer.mapper.ObjectMapper;
//import com.wanderfun.domainlayer.repository.UserRepository;
//import com.wanderfun.domainlayer.model.users.User;
//import com.wanderfun.domainlayer.model.users.UserRole;
//import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class UserRepositoryImpl extends BaseRepositoryImpl<User, UserEntity, Long> implements UserRepository {
//    private final JpaUserRepository jpaUserRepository;
//
//    @Autowired
//    public UserRepositoryImpl(JpaUserRepository jpaUserRepository, ObjectMapper objectMapper) {
//        super(jpaUserRepository, objectMapper, User.class, UserEntity.class);
//        this.jpaUserRepository = jpaUserRepository;
//    }
//
//    @Override
//    public Optional<User> findByEmail (String email) {
//        return jpaUserRepository.findByEmail(email)
//                    .map(entity -> objectMapper.map(entity, User.class));
//    }
//
//    @Override
//    public List<User> findAllByRole(UserRole role) {
//        return objectMapper.mapList(jpaUserRepository.findAllByRole(role.name()), User.class);
//    }
//
//    @Override
//    public List<User> findByOrderByPointDesc() {
//        return objectMapper.mapList(jpaUserRepository.findByRoleOrderByPointDesc(UserRole.USER.name()), User.class);
//    }
//}
