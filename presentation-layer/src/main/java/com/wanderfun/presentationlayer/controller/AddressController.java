package com.wanderfun.presentationlayer.controller;

import com.wanderfun.applicationlayer.dto.ResponseDto;
import com.wanderfun.applicationlayer.dto.addresses.DistrictDto;
import com.wanderfun.applicationlayer.dto.addresses.ProvinceDto;
import com.wanderfun.applicationlayer.dto.addresses.WardDto;
import com.wanderfun.applicationlayer.usecase.AddressUsecase;
import com.wanderfun.presentationlayer.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("wanderfun/address")
public class AddressController {
    private final AddressUsecase addressUsecase;

    @Autowired
    public AddressController(AddressUsecase addressUsecase) {
        this.addressUsecase = addressUsecase;
    }

    @GetMapping("/province")
    public ResponseEntity<ResponseDto<List<ProvinceDto>>> findAllProvinces() {
        List<ProvinceDto> result = addressUsecase.findAllProvinces();
        if (result == null) {
            throw new RequestFailedException("Find all province failed!");
        }

        ResponseDto<List<ProvinceDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all province successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/province/search/{key}")
    public ResponseEntity<ResponseDto<List<ProvinceDto>>> findAllProvincesByNameContaining(@PathVariable String key) {
        List<ProvinceDto> result = addressUsecase.findAllProvincesByNameContaining(key);
        if (result == null) {
            throw new RequestFailedException("Find all province by name containing failed!");
        }

        ResponseDto<List<ProvinceDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all province by name containing successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/province/{name}")
    public ResponseEntity<ResponseDto<ProvinceDto>> findProvinceByName(@PathVariable String name) {
        ProvinceDto result = addressUsecase.findProvinceByName(name);
        if (result == null) {
            throw new RequestFailedException("Find province by name failed!");
        }

        ResponseDto<ProvinceDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find province by name successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/district/{provinceCode}")
    public ResponseEntity<ResponseDto<List<DistrictDto>>> findAllDistrictsByProvinceCode(@PathVariable String provinceCode) {
        List<DistrictDto> result = addressUsecase.findAllDistrictsByProvinceCode(provinceCode);
        if (result == null) {
            throw new RequestFailedException("Find all district by province code failed!");
        }

        ResponseDto<List<DistrictDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all district by province code successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/district/{name}/{provinceCode}")
    public ResponseEntity<ResponseDto<DistrictDto>> findDistrictByNameAndProvinceCode(@PathVariable String name,
                                                                       @PathVariable String provinceCode) {
        DistrictDto result = addressUsecase.findDistrictByNameAndProvinceCode(name, provinceCode);
        if (result == null) {
            throw new RequestFailedException("Find district by name and province code failed!");
        }

        ResponseDto<DistrictDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find district by name and province code successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/ward/{districtCode}")
    public ResponseEntity<ResponseDto<List<WardDto>>> findAllWardsByDistrictCode(@PathVariable String districtCode) {
        List<WardDto> result = addressUsecase.findAllWardsByDistrictCode(districtCode);
        if (result == null) {
            throw new RequestFailedException("Find all ward by district code failed!");
        }

        ResponseDto<List<WardDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all ward by district code successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/ward/{name}/{districtCode}")
    public ResponseEntity<ResponseDto<WardDto>> findWardByNameAndDistrictCode(@PathVariable String name,
                                                                   @PathVariable String districtCode) {
        WardDto result = addressUsecase.findWardByNameAndDistrictCode(name, districtCode);
        if (result == null) {
            throw new RequestFailedException("Find ward by name and district code failed!");
        }

        ResponseDto<WardDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find ward by name and district code successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
