package com.wanderfun.infrastructurelayer.persistence.jpaRepository.users;

import com.wanderfun.infrastructurelayer.persistence.entity.users.UserEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaBaseRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.account.id = :account_id")
    Optional<UserEntity> findByAccountId(@Param("account_id")Long accountId);
}
