package com.wanderfun.applicationlayer.service;

import com.wanderfun.applicationlayer.dto.cloudinary.CloudinarySignatureDto;

public interface CloudinaryService {
    CloudinarySignatureDto getSignature(String timestamp);
    void deleteImage(String publicId);
}
