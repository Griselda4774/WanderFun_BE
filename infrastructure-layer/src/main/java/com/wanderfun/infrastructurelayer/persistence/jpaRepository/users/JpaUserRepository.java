package com.wanderfun.infrastructurelayer.persistence.jpaRepository.users;

import com.wanderfun.infrastructurelayer.persistence.entity.users.UserEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaBaseRepository<UserEntity, Long> {
}
