package com.wanderfun.infrastructurelayer.persistence.jpaRepository.addresses;

import com.wanderfun.domainlayer.repository.BaseRepository;
import com.wanderfun.infrastructurelayer.persistence.entity.addresses.AddressEntity;

public interface JpaAddressRepository extends BaseRepository<AddressEntity, Long> {
}
