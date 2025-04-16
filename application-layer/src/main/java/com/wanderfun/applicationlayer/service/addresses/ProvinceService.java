package com.wanderfun.applicationlayer.service.addresses;

import com.wanderfun.applicationlayer.service.BaseService;
import com.wanderfun.domainlayer.model.addresses.Province;

import java.util.List;

public interface ProvinceService extends BaseService<Province, String> {
    List<Province> findAllByNameContaining(String name);
}
