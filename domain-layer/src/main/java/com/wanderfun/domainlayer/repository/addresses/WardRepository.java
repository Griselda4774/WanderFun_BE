package com.wanderfun.domainlayer.repository.addresses;

import com.wanderfun.domainlayer.model.addresses.Ward;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.List;

public interface WardRepository extends BaseRepository<Ward, String> {
    List<Ward> findAllByDistrictCode(String districtCode);
}
