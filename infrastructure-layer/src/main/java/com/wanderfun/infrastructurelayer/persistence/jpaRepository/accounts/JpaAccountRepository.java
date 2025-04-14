package com.wanderfun.infrastructurelayer.persistence.jpaRepository.accounts;

import com.wanderfun.infrastructurelayer.persistence.entity.accounts.AccountEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaAccountRepository extends JpaBaseRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByEmail(String email);
}
