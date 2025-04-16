package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.domainlayer.model.addresses.Address;
import com.wanderfun.domainlayer.model.addresses.District;
import com.wanderfun.domainlayer.model.addresses.Province;
import com.wanderfun.domainlayer.model.addresses.Ward;

import java.util.List;

public interface AddressUsecase {
    List<Province> findAllProvinces();
    List<Province> findAllProvincesByNameContaining(String name);
    List<District> findAllByProvinceCode(String provinceCode);
    List<Ward> findAllByDistrictCode(String districtCode);
    Address findAddressById(Long id);
    boolean createAddress(Address address);
    boolean updateAddressById(Long id, Address address);
}
