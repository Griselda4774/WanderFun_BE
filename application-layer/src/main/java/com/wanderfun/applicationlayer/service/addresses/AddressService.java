package com.wanderfun.applicationlayer.service.addresses;

import com.wanderfun.applicationlayer.service.BaseService;
import com.wanderfun.domainlayer.model.addresses.Address;

public interface AddressService extends BaseService<Address, Long> {
    Address findExistingAddress(String provinceCode,
                                String districtCode,
                                String wardCode,
                                String street);
}
