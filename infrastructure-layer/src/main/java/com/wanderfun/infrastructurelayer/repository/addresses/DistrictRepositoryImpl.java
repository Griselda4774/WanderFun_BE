package com.wanderfun.infrastructurelayer.repository.addresses;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.addresses.District;
import com.wanderfun.domainlayer.repository.addresses.DistrictRepository;
import com.wanderfun.infrastructurelayer.persistence.entity.addresses.DistrictEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.addresses.JpaDistrictRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DistrictRepositoryImpl extends BaseRepositoryImpl<District, DistrictEntity, String> implements DistrictRepository {
    private final JpaDistrictRepository jpaDistrictRepository;

    @Autowired
    public DistrictRepositoryImpl(JpaDistrictRepository jpaDistrictRepository, ObjectMapper objectMapper) {
        super(jpaDistrictRepository, objectMapper, District.class, DistrictEntity.class);
        this.jpaDistrictRepository = jpaDistrictRepository;
    }

    @Override
    public List<District> findAllByProvinceCode(String provinceCode) {
        return objectMapper.mapList(jpaDistrictRepository.findAllByProvince_Code(provinceCode), District.class);
    }

    @Override
    public Optional<District> findByNameAndProvinceCode(String name, String provinceCode) {
        return jpaDistrictRepository.findByNameAndProvince_Code(name, provinceCode)
                .map(districtEntity -> objectMapper.map(districtEntity, District.class));
    }
}
