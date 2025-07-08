package com.wanderfun.domainlayer.repository.auths;

import com.wanderfun.domainlayer.model.auths.MailOtp;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.Optional;

public interface MailOtpRepository extends BaseRepository<MailOtp, Long> {
    Optional<MailOtp> findByEmailAndOtp(String email, String otp);
}
