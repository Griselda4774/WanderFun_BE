package com.wanderfun.infrastructurelayer.service.addresses;

import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.addresses.DistrictService;
import com.wanderfun.domainlayer.model.addresses.District;
import com.wanderfun.domainlayer.repository.addresses.DistrictRepository;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl extends BaseServiceImpl<District, String> implements DistrictService {
    private final DistrictRepository districtRepository;

    public DistrictServiceImpl(DistrictRepository districtRepository, ObjectMapper objectMapper) {
        super(districtRepository, objectMapper, District.class);
        this.districtRepository = districtRepository;
    }

    @Override
    @Transactional
    public List<District> findAllByProvinceCode(String provinceCode) {
        return objectMapper.mapList(districtRepository.findAllByProvinceCode(provinceCode), District.class);
    }

    @Override
    @Transactional
    public District findByNameAndProvinceCode(String name, String provinceCode) {
        return districtRepository.findByNameAndProvinceCode(name, provinceCode)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", District.class.getSimpleName())));
    }
}
