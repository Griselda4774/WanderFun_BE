package com.wanderfun.domainlayer.repository.addresses;

import com.wanderfun.domainlayer.model.addresses.Address;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.Optional;

public interface AddressRepository extends BaseRepository<Address, Long> {
    Optional<Address> findExistingAddress(String provinceCode,
                                          String districtCode,
                                          String wardCode,
                                          String street);
}
