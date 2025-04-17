package com.wanderfun.infrastructurelayer.usecase;

import com.wanderfun.applicationlayer.dto.addresses.AddressDto;
import com.wanderfun.applicationlayer.dto.addresses.DistrictDto;
import com.wanderfun.applicationlayer.dto.addresses.ProvinceDto;
import com.wanderfun.applicationlayer.dto.addresses.WardDto;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.addresses.DistrictService;
import com.wanderfun.applicationlayer.service.addresses.ProvinceService;
import com.wanderfun.applicationlayer.service.addresses.WardService;
import com.wanderfun.applicationlayer.usecase.AddressUsecase;
import com.wanderfun.domainlayer.model.addresses.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressUsecaseImpl implements AddressUsecase {

    private final ProvinceService provinceService;
    private final ObjectMapper objectMapper;
    private final DistrictService districtService;
    private final WardService wardService;

    public AddressUsecaseImpl(ProvinceService provinceService, ObjectMapper objectMapper, DistrictService districtService, WardService wardService) {
        this.provinceService = provinceService;
        this.objectMapper = objectMapper;
        this.districtService = districtService;
        this.wardService = wardService;
    }

    @Override
    public List<ProvinceDto> findAllProvinces() {
        return objectMapper.mapList(provinceService.findAll(), ProvinceDto.class);
    }

    @Override
    public List<ProvinceDto> findAllProvincesByNameContaining(String name) {
        return objectMapper.mapList(provinceService.findAllByNameContaining(name), ProvinceDto.class);
    }

    @Override
    public ProvinceDto findProvinceByName(String name) {
        return objectMapper.map(provinceService.findByName(name), ProvinceDto.class);
    }

    @Override
    public List<DistrictDto> findAllDistrictsByProvinceCode(String provinceCode) {
        return objectMapper.mapList(districtService.findAllByProvinceCode(provinceCode), DistrictDto.class);
    }

    @Override
    public DistrictDto findDistrictByNameAndProvinceCode(String name, String provinceCode) {
        return objectMapper.map(districtService.findByNameAndProvinceCode(name, provinceCode), DistrictDto.class);
    }

    @Override
    public List<WardDto> findAllWardsByDistrictCode(String districtCode) {
        return objectMapper.mapList(wardService.findAllByDistrictCode(districtCode), WardDto.class);
    }

    @Override
    public WardDto findWardByNameAndDistrictCode(String name, String districtCode) {
        return objectMapper.map(wardService.findByNameAndDistrictCode(name, districtCode), WardDto.class);
    }
}
