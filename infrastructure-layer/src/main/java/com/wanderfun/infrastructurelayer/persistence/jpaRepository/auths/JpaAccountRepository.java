package com.wanderfun.infrastructurelayer.persistence.jpaRepository.auths;

import com.wanderfun.infrastructurelayer.persistence.entity.auths.AccountEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaAccountRepository extends JpaBaseRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByEmail(String email);

    @Query("""
        SELECT a FROM AccountEntity a
        WHERE a.role = 'USER'
    """)
    List<AccountEntity> findAllUserAccount();

    @Query("""
    SELECT COUNT(a) FROM AccountEntity a
    WHERE DATE(a.createAt) = CURRENT_DATE
    """)
    Long countAccountsCreatedToday();
}
