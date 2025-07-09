package com.wanderfun.infrastructurelayer.persistence.jpaRepository.auths;

import com.wanderfun.infrastructurelayer.persistence.entity.auths.MailOtpEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaMailOtpRepository extends JpaBaseRepository<MailOtpEntity, Long> {
    @Query("SELECT m FROM MailOtpEntity m WHERE m.email = :email AND m.otp = :otp")
    Optional<MailOtpEntity> findByEmailAndOtp(@Param("email") String email,
                                              @Param("otp") String otp);
}
