package com.wanderfun.infrastructurelayer.persistence.jpaRepository.addresses;

import com.wanderfun.infrastructurelayer.persistence.entity.addresses.ProvinceDetailEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaProvinceDetailRepository extends JpaBaseRepository<ProvinceDetailEntity, Integer> {
    @Query("SELECT p FROM ProvinceDetailEntity p WHERE p.province.code = :province_code")
    Optional<ProvinceDetailEntity> findByProvince_Code(@Param("province_code")String provinceCode);
}
