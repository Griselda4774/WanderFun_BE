//package com.wanderfun.infrastructurelayer.service;
//
//import com.cloudinary.Cloudinary;
//import com.cloudinary.utils.ObjectUtils;
//import com.wanderfun.applicationlayer.dto.cloudinary.CloudinarySignatureDto;
//import com.wanderfun.applicationlayer.exception.CloudinaryDeleteImageFailedException;
//import com.wanderfun.applicationlayer.exception.GenerateCloudinarySignatureFailedException;
//import com.wanderfun.applicationlayer.service.CloudinaryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CloudinaryServiceImpl implements CloudinaryService {
//
//    private final Cloudinary cloudinary;
//
//    @Autowired
//    public CloudinaryServiceImpl(Cloudinary cloudinary) {
//        this.cloudinary = cloudinary;
//    }
//
//    @Override
//    public CloudinarySignatureDto getSignature(String timestamp) throws GenerateCloudinarySignatureFailedException {
//        try {
//            String signature = cloudinary.apiSignRequest(
//                    ObjectUtils.asMap("timestamp", timestamp),
//                    cloudinary.config.apiSecret
//            );
//            CloudinarySignatureDto cloudinarySignatureDto = new CloudinarySignatureDto();
//            cloudinarySignatureDto.setSignature(signature);
//            cloudinarySignatureDto.setApiKey(cloudinary.config.apiKey);
//            cloudinarySignatureDto.setCloudName(cloudinary.config.cloudName);
//            return cloudinarySignatureDto;
//        } catch (Exception e) {
//            throw new GenerateCloudinarySignatureFailedException("Failed to generate cloudinary signature");
//        }
//    }
//
//    @Override
//    public void deleteImage(String publicId) throws CloudinaryDeleteImageFailedException {
//        try {
//            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
//        } catch (Exception e) {
//            throw new CloudinaryDeleteImageFailedException("Failed to delete image from cloudinary");
//        }
//    }
//}
