package com.wanderfun.domainlayer.repository.addresses;

import com.wanderfun.domainlayer.model.addresses.ProvinceDetail;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.Optional;

public interface ProvinceDetailRepository extends BaseRepository<ProvinceDetail, Integer> {
    Optional<ProvinceDetail> findByProvince_Code(String provinceCode);
}
