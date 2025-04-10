package com.wanderfun.infrastructurelayer.configuration.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "justvvu",
                "api_key", "673892793273773",
                "api_secret", "X29Z9tizusVNpGbaahWBUQ_jwqc"
        ));
    }
}
