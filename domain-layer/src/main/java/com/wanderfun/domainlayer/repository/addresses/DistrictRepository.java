package com.wanderfun.domainlayer.repository.addresses;

import com.wanderfun.domainlayer.model.addresses.District;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.List;

public interface DistrictRepository extends BaseRepository<District, String> {
    List<District> findAllByProvinceCode(String provinceCode);
}
