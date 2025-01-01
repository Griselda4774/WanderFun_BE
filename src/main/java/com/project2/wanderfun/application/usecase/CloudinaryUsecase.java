package com.project2.wanderfun.application.usecase;

import com.project2.wanderfun.application.dto.cloudinary.CloudinarySignatureDto;
import com.project2.wanderfun.application.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CloudinaryUsecase {
    private final CloudinaryService cloudinaryService;

    @Autowired
    public CloudinaryUsecase(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    public CloudinarySignatureDto getSignature(String timestamp) {
        return cloudinaryService.getSignature(timestamp);
    }

    public boolean deleteImage(String publicId) {
        cloudinaryService.deleteImage(publicId);
        return true;
    }
}
