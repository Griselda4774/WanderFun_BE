package com.wanderfun.domainlayer.repository.addresses;

import com.wanderfun.domainlayer.model.addresses.District;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository extends BaseRepository<District, String> {
    List<District> findAllByProvinceCode(String provinceCode);
    Optional<District> findByNameAndProvinceCode(String name, String provinceCode);
}
