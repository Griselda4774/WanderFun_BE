package com.wanderfun.infrastructurelayer.service.addresses;

import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.addresses.ProvinceService;
import com.wanderfun.domainlayer.model.addresses.Province;
import com.wanderfun.domainlayer.repository.addresses.ProvinceRepository;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl extends BaseServiceImpl<Province, String> implements ProvinceService {
    private final ProvinceRepository provinceRepository;

    @Autowired
    public ProvinceServiceImpl(ProvinceRepository provinceRepository, ObjectMapper objectMapper) {
        super(provinceRepository, objectMapper, Province.class);
        this.provinceRepository = provinceRepository;
    }

    @Override
    @Transactional
    public List<Province> findAllByNameContaining(String name) {
        return objectMapper.mapList(provinceRepository.findAllByNameContaining(name), Province.class);
    }

    @Override
    @Transactional
    public Province findByName(String name) {
        return provinceRepository.findByName(name)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", Province.class.getSimpleName())));
    }
}
