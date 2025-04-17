package com.wanderfun.domainlayer.repository.addresses;

import com.wanderfun.domainlayer.model.addresses.Ward;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface WardRepository extends BaseRepository<Ward, String> {
    List<Ward> findAllByDistrictCode(String districtCode);
    Optional<Ward> findByNameAndDistrictCode(String name, String districtCode);
}
