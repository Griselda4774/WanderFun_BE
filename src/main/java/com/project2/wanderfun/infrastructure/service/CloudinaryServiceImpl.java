package com.project2.wanderfun.infrastructure.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.project2.wanderfun.application.dto.cloudinary.CloudinarySignatureDto;
import com.project2.wanderfun.application.exception.CloudinaryDeleteImageFailedException;
import com.project2.wanderfun.application.exception.GenerateCloudinarySignatureFailedException;
import com.project2.wanderfun.application.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public CloudinarySignatureDto getSignature(String timestamp) throws GenerateCloudinarySignatureFailedException {
        try {
            String signature = cloudinary.apiSignRequest(
                    ObjectUtils.asMap("timestamp", timestamp),
                    cloudinary.config.apiSecret
            );
            CloudinarySignatureDto cloudinarySignatureDto = new CloudinarySignatureDto();
            cloudinarySignatureDto.setSignature(signature);
            cloudinarySignatureDto.setApiKey(cloudinary.config.apiKey);
            cloudinarySignatureDto.setCloudName(cloudinary.config.cloudName);
            return cloudinarySignatureDto;
        } catch (Exception e) {
            throw new GenerateCloudinarySignatureFailedException("Failed to generate cloudinary signature");
        }
    }

    @Override
    public void deleteImage(String publicId) throws CloudinaryDeleteImageFailedException {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (Exception e) {
            throw new CloudinaryDeleteImageFailedException("Failed to delete image from cloudinary");
        }
    }
}
