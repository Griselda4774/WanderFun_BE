package com.wanderfun.presentationlayer.controller;

import com.wanderfun.applicationlayer.dto.ResponseDto;
import com.wanderfun.applicationlayer.dto.cloudinary.CloudinarySignatureDto;
import com.wanderfun.applicationlayer.usecase.CloudinaryUsecase;
import com.wanderfun.presentationlayer.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("wanderfun/cloudinary")
public class CloudinaryController {
    private final CloudinaryUsecase cloudinaryUsecase;

    @Autowired
    public CloudinaryController(CloudinaryUsecase cloudinaryUsecase) {
        this.cloudinaryUsecase = cloudinaryUsecase;
    }

    @GetMapping("/signature")
    public ResponseEntity<ResponseDto<CloudinarySignatureDto>> getSignature(@RequestParam String timestamp)
            throws RequestFailedException {

        CloudinarySignatureDto result = cloudinaryUsecase.getSignature(timestamp);
        if (result == null) {
            throw new RequestFailedException("Get cloudinary signature failed!");
        }

        ResponseDto<CloudinarySignatureDto> response = new ResponseDto();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Get cloudinary signature successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseDto<CloudinarySignatureDto>> deleteImage(@RequestParam String publicId) {
        boolean result = cloudinaryUsecase.deleteImage(publicId);

        if (!result) {
            throw new RequestFailedException("Delete image failed!");
        }

        ResponseDto<CloudinarySignatureDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete image successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
