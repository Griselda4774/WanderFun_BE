package com.wanderfun.applicationlayer.service.addresses;

import com.wanderfun.applicationlayer.service.BaseService;
import com.wanderfun.domainlayer.model.addresses.Ward;

import java.util.List;

public interface WardService extends BaseService<Ward, String> {
    List<Ward> findAllByDistrictCode(String districtCode);
    Ward findByNameAndDistrictCode(String name, String districtCode);
}
