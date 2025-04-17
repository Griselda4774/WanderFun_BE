package com.wanderfun.infrastructurelayer.persistence.jpaRepository.addresses;

import com.wanderfun.infrastructurelayer.persistence.entity.addresses.DistrictEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaDistrictRepository extends JpaBaseRepository<DistrictEntity, String> {
    @Query("SELECT d FROM DistrictEntity d WHERE d.province.code = :province_code")
    List<DistrictEntity> findAllByProvince_Code(@Param("province_code") String provinceCode);

    @Query("SELECT d FROM DistrictEntity d WHERE d.name = :name AND d.province.code = :province_code")
    Optional<DistrictEntity> findByNameAndProvince_Code(@Param("name")String name,
                                                        @Param("province_code")String provinceCode);
}
