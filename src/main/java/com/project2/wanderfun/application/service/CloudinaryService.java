package com.project2.wanderfun.application.service;

import com.project2.wanderfun.application.dto.cloudinary.CloudinarySignatureDto;

public interface CloudinaryService {
    CloudinarySignatureDto getSignature(String timestamp);
    void deleteImage(String publicId);
}
