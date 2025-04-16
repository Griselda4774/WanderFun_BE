package com.wanderfun.infrastructurelayer.persistence.jpaRepository.addresses;

import com.wanderfun.infrastructurelayer.persistence.entity.addresses.DistrictEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaDistrictRepository extends JpaBaseRepository<DistrictEntity, String> {
    @Query("""
        SELECT d FROM DistrictEntity d
        WHERE d.province.code = :province_code
    """)
    List<DistrictEntity> findAllByProvince_Code(@Param("province_code") String provinceCode);
}
