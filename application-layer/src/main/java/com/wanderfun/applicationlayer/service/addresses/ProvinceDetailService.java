package com.wanderfun.applicationlayer.service.addresses;

import com.wanderfun.applicationlayer.service.BaseService;
import com.wanderfun.domainlayer.model.addresses.ProvinceDetail;

public interface ProvinceDetailService extends BaseService<ProvinceDetail, Integer> {
    ProvinceDetail findByProvinceCode(String provinceCode);
}
