package com.wanderfun.applicationlayer.service.auths;

import com.wanderfun.applicationlayer.service.BaseService;
import com.wanderfun.domainlayer.model.auths.MailOtp;

public interface MailOtpService extends BaseService<MailOtp, Long> {
    MailOtp findByEmailAndOtp(String email, String otp);
}
