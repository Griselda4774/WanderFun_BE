package com.wanderfun.infrastructurelayer.service.addresses;

import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.addresses.AddressService;
import com.wanderfun.domainlayer.model.addresses.Address;
import com.wanderfun.domainlayer.repository.addresses.AddressRepository;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends BaseServiceImpl<Address, Long> implements AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, ObjectMapper objectMapper) {
        super(addressRepository, objectMapper, Address.class);
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public Address findExistingAddress(String provinceCode, String districtCode, String wardCode, String street) {
        return addressRepository.findExistingAddress(provinceCode, districtCode, wardCode, street)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", Address.class.getSimpleName())));
    }
}
