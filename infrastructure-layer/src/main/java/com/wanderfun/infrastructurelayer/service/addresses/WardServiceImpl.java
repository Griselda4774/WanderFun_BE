package com.wanderfun.infrastructurelayer.service.addresses;

import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.addresses.WardService;
import com.wanderfun.domainlayer.model.addresses.Ward;
import com.wanderfun.domainlayer.repository.addresses.WardRepository;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardServiceImpl extends BaseServiceImpl<Ward, String> implements WardService {
    private final WardRepository wardRepository;

    public WardServiceImpl(WardRepository wardRepository, ObjectMapper objectMapper) {
        super(wardRepository, objectMapper, Ward.class);
        this.wardRepository = wardRepository;
    }

    @Override
    @Transactional
    public List<Ward> findAllByDistrictCode(String districtCode) {
        return objectMapper.mapList(wardRepository.findAllByDistrictCode(districtCode), Ward.class);
    }

    @Override
    @Transactional
    public Ward findByNameAndDistrictCode(String name, String districtCode) {
        return wardRepository.findByNameAndDistrictCode(name, districtCode)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", Ward.class.getSimpleName())));
    }
}
