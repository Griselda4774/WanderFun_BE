package com.wanderfun.infrastructurelayer.repository.addresses;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.addresses.Province;
import com.wanderfun.domainlayer.repository.addresses.ProvinceRepository;
import com.wanderfun.infrastructurelayer.persistence.entity.addresses.ProvinceEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.addresses.JpaProvinceRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProvinceRepositoryImpl extends BaseRepositoryImpl<Province, ProvinceEntity, String> implements ProvinceRepository {
    private final JpaProvinceRepository jpaProvinceRepository;

    @Autowired
    public ProvinceRepositoryImpl(JpaProvinceRepository jpaProvinceRepository, ObjectMapper objectMapper) {
        super(jpaProvinceRepository, objectMapper, Province.class, ProvinceEntity.class);
        this.jpaProvinceRepository = jpaProvinceRepository;
    }

    @Override
    public List<Province> findAllByNameContaining(String name) {
        return objectMapper.mapList(jpaProvinceRepository.findAllByNameContaining(name), Province.class);
    }

    @Override
    public Optional<Province> findByName(String name) {
        return jpaProvinceRepository.findByName(name)
                .map(provinceEntity -> objectMapper.map(provinceEntity, Province.class));
    }
}
