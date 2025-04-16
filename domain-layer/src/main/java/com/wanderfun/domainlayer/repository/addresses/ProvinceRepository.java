package com.wanderfun.domainlayer.repository.addresses;

import com.wanderfun.domainlayer.model.addresses.Province;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.List;

public interface ProvinceRepository extends BaseRepository<Province, String> {
    List<Province> findAllByNameContaining(String name);
}
