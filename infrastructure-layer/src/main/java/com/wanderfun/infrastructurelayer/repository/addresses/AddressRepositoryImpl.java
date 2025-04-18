package com.wanderfun.infrastructurelayer.repository.addresses;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.addresses.Address;
import com.wanderfun.domainlayer.repository.addresses.AddressRepository;
import com.wanderfun.infrastructurelayer.persistence.entity.addresses.AddressEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.addresses.JpaAddressRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AddressRepositoryImpl extends BaseRepositoryImpl<Address, AddressEntity, Long> implements AddressRepository {
    private final JpaAddressRepository jpaAddressRepository;

    @Autowired
    public AddressRepositoryImpl(JpaAddressRepository jpaAddressRepository, ObjectMapper objectMapper) {
        super(jpaAddressRepository, objectMapper, Address.class, AddressEntity.class);
        this.jpaAddressRepository = jpaAddressRepository;
    }

    @Override
    public Optional<Address> findExistingAddress(String provinceCode, String districtCode, String wardCode, String street) {
        return jpaAddressRepository.findExistingAddress(provinceCode, districtCode, wardCode, street)
                .map(addressEntity -> objectMapper.map(addressEntity, Address.class));
    }

    @Override
    public Address save(Address address) {
        AddressEntity addressEntity = objectMapper.map(address, AddressEntity.class);

        if (address.getWard().getCode() == null) {
            addressEntity.setWard(null);
        }

        AddressEntity savedAddressEntity = jpaAddressRepository.save(addressEntity);
        return objectMapper.map(savedAddressEntity, Address.class);
    }
}
