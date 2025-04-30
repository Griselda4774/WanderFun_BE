package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.applicationlayer.dto.addresses.DistrictDto;
import com.wanderfun.applicationlayer.dto.addresses.ProvinceDto;
import com.wanderfun.applicationlayer.dto.addresses.WardDto;
import com.wanderfun.domainlayer.model.addresses.ProvinceDetail;

import java.util.List;

public interface AddressUsecase {
    List<ProvinceDto> findAllProvinces();
    List<ProvinceDto> findAllProvincesByNameContaining(String name);
    ProvinceDto findProvinceByName(String name);
    List<DistrictDto> findAllDistrictsByProvinceCode(String provinceCode);
    DistrictDto findDistrictByNameAndProvinceCode(String name, String provinceCode);
    List<WardDto> findAllWardsByDistrictCode(String districtCode);
    WardDto findWardByNameAndDistrictCode(String name, String districtCode);
    ProvinceDetail findAllProvinceDetails();
    ProvinceDetail findProvinceDetailByProvinceCode(String provinceCode);
    boolean createProvinceDetail(ProvinceDetail provinceDetail);
    boolean createAllProvinceDetails(List<ProvinceDetail> provinceDetails);
    boolean updateProvinceDetailById(Integer id, ProvinceDetail provinceDetail);
    boolean deleteProvinceDetailById(Integer id);
}
