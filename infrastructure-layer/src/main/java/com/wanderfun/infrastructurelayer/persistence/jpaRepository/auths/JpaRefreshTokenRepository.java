package com.wanderfun.infrastructurelayer.persistence.jpaRepository.auths;

import com.wanderfun.infrastructurelayer.persistence.entity.auths.RefreshTokenEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaRefreshTokenRepository extends JpaBaseRepository<RefreshTokenEntity, Long> {
    @Query("SELECT rt FROM RefreshTokenEntity rt WHERE rt.account.id = :accountId")
    Optional<RefreshTokenEntity> findByAccountId(@Param("accountId")Long accountId);
    void deleteByAccount_Id(Long accountId);
}
