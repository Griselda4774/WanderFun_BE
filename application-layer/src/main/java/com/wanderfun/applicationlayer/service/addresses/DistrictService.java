package com.wanderfun.applicationlayer.service.addresses;

import com.wanderfun.applicationlayer.service.BaseService;
import com.wanderfun.domainlayer.model.addresses.District;

import java.util.List;

public interface DistrictService extends BaseService<District, String> {
    List<District> findAllByProvinceCode(String provinceCode);
    District findByNameAndProvinceCode(String name, String provinceCode);
}
