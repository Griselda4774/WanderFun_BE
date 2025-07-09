package com.wanderfun.infrastructurelayer.service.auths;

import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.auths.MailOtpService;
import com.wanderfun.domainlayer.model.auths.MailOtp;
import com.wanderfun.domainlayer.repository.auths.MailOtpRepository;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailOtpServiceImpl extends BaseServiceImpl<MailOtp, Long> implements MailOtpService {
    private final MailOtpRepository mailOtpRepository;

    @Autowired
    public MailOtpServiceImpl(MailOtpRepository mailOtpRepository, ObjectMapper objectMapper) {
        super(mailOtpRepository, objectMapper, MailOtp.class);
        this.mailOtpRepository = mailOtpRepository;
    }

    @Override
    public MailOtp findByEmailAndOtp(String email, String otp) {
        return mailOtpRepository.findByEmailAndOtp(email, otp)
                .orElseThrow(() -> new ObjectNotFoundException("Mail OTP not found for email: " + email + " and OTP: " + otp));
    }
}
