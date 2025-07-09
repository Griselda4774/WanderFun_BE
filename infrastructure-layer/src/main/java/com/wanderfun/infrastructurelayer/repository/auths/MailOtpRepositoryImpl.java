package com.wanderfun.infrastructurelayer.repository.auths;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.auths.MailOtp;
import com.wanderfun.domainlayer.repository.auths.MailOtpRepository;
import com.wanderfun.infrastructurelayer.persistence.entity.auths.MailOtpEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.auths.JpaMailOtpRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MailOtpRepositoryImpl extends BaseRepositoryImpl<MailOtp, MailOtpEntity, Long> implements MailOtpRepository {
    private final JpaMailOtpRepository jpaMailOtpRepository;

    @Autowired
    public MailOtpRepositoryImpl(JpaMailOtpRepository jpaMailOtpRepository, ObjectMapper objectMapper) {
        super(jpaMailOtpRepository, objectMapper, MailOtp.class, MailOtpEntity.class);
        this.jpaMailOtpRepository = jpaMailOtpRepository;
    }

    @Override
    public Optional<MailOtp> findByEmailAndOtp(String email, String otp) {
        return jpaMailOtpRepository.findByEmailAndOtp(email, otp)
                .map(entity -> objectMapper.map(entity, MailOtp.class));
    }
}
