package com.wanderfun.infrastructurelayer.repository.addresses;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.addresses.Ward;
import com.wanderfun.domainlayer.repository.addresses.WardRepository;
import com.wanderfun.infrastructurelayer.persistence.entity.addresses.WardEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.addresses.JpaWardRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WardRepositoryImpl extends BaseRepositoryImpl<Ward, WardEntity, String> implements WardRepository {
    private final JpaWardRepository jpaWardRepository;

    @Autowired
    public WardRepositoryImpl(JpaWardRepository jpaWardRepository, ObjectMapper objectMapper) {
        super(jpaWardRepository, objectMapper, Ward.class, WardEntity.class);
        this.jpaWardRepository = jpaWardRepository;
    }

    @Override
    public List<Ward> findAllByDistrictCode(String districtCode) {
        return objectMapper.mapList(jpaWardRepository.findAllByDistrict_Code(districtCode), Ward.class);
    }

    @Override
    public Optional<Ward> findByNameAndDistrictCode(String name, String districtCode) {
        return jpaWardRepository.findByNameAndDistrict_Code(name, districtCode)
                .map(wardEntity -> objectMapper.map(wardEntity, Ward.class));
    }
}
