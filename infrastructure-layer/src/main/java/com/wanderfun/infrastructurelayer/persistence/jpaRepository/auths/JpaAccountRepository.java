package com.wanderfun.infrastructurelayer.persistence.jpaRepository.auths;

import com.wanderfun.infrastructurelayer.persistence.entity.auths.AccountEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaAccountRepository extends JpaBaseRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByEmail(String email);
}
