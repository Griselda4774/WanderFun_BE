package com.wanderfun.presentationlayer.controller;

import com.wanderfun.applicationlayer.dto.ResponseDto;
import com.wanderfun.applicationlayer.dto.addresses.ProvinceDto;
import com.wanderfun.applicationlayer.dto.places.MiniPlaceDto;
import com.wanderfun.applicationlayer.usecase.AutoCompleteSearchUsecase;
import com.wanderfun.presentationlayer.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("wanderfun/autocomplete")
public class AutoCompleteSearchController {
    private final AutoCompleteSearchUsecase autoCompleteSearchUsecase;

    @Autowired
    public AutoCompleteSearchController(AutoCompleteSearchUsecase autoCompleteSearchUsecase) {
        this.autoCompleteSearchUsecase = autoCompleteSearchUsecase;
    }

    @GetMapping("/place")
    public ResponseEntity<ResponseDto<List<MiniPlaceDto>>> autoCompleteSearchPlace(@RequestParam String keyword) {
        List<MiniPlaceDto> result = autoCompleteSearchUsecase.autoCompleteSearchPlace(keyword);
        if (result == null) {
            throw new RequestFailedException("Auto complete place failed!");
        }

        ResponseDto<List<MiniPlaceDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Auto complete place successful!");
        response.setData(result);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/province")
    public ResponseEntity<ResponseDto<List<ProvinceDto>>> autoCompleteSearchProvince(@RequestParam String keyword) {
        List<ProvinceDto> result = autoCompleteSearchUsecase.autoCompleteSearchProvince(keyword);
        if (result == null) {
            throw new RequestFailedException("Auto complete province failed!");
        }

        ResponseDto<List<ProvinceDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Auto complete province successful!");
        response.setData(result);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
