package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.applicationlayer.dto.addresses.DistrictDto;
import com.wanderfun.applicationlayer.dto.addresses.ProvinceDto;
import com.wanderfun.applicationlayer.dto.addresses.WardDto;

import java.util.List;

public interface AddressUsecase {
    List<ProvinceDto> findAllProvinces();
    List<ProvinceDto> findAllProvincesByNameContaining(String name);
    ProvinceDto findProvinceByName(String name);
    List<DistrictDto> findAllDistrictsByProvinceCode(String provinceCode);
    DistrictDto findDistrictByNameAndProvinceCode(String name, String provinceCode);
    List<WardDto> findAllWardsByDistrictCode(String districtCode);
    WardDto findWardByNameAndDistrictCode(String name, String districtCode);
//    Address findAddressById(Long id);
//    boolean createAddress(Address address);
//    boolean updateAddressById(Long id, Address address);
}
