package com.wanderfun.infrastructurelayer.persistence.jpaRepository.addresses;

import com.wanderfun.infrastructurelayer.persistence.entity.addresses.AddressEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaAddressRepository extends JpaBaseRepository<AddressEntity, Long> {
    @Query("""
        SELECT a FROM AddressEntity a
        WHERE ((:street IS NULL AND a.street IS NULL) OR (:street IS NOT NULL AND a.street = :street))
        AND ((:ward_code IS NULL AND a.ward IS NULL) OR (:ward_code IS NOT NULL AND a.ward.code = :ward_code))
        AND a.district.code = :district_code
        AND a.province.code = :province_code
    """)
    Optional<AddressEntity> findExistingAddress(@Param("province_code") String provinceCode,
                                                @Param("district_code") String districtCode,
                                                @Param("ward_code") String wardCode,
                                                @Param("street") String street);
}
