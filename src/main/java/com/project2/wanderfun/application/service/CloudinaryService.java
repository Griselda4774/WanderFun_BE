package com.project2.wanderfun.application.service;

import com.project2.wanderfun.application.dto.cloudinary.CloudinarySignatureDto;

public interface CloudinaryService {
    public CloudinarySignatureDto getSignature(String timestamp);
    public void deleteImage(String publicId);
}
