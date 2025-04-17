package com.wanderfun.infrastructurelayer.persistence.jpaRepository.addresses;

import com.wanderfun.infrastructurelayer.persistence.entity.addresses.DistrictEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.addresses.WardEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaWardRepository extends JpaBaseRepository<WardEntity, String> {
    @Query("SELECT w FROM WardEntity w WHERE w.district.code = :district_code")
    List<WardEntity> findAllByDistrict_Code(@Param("district_code") String districtCode);

    @Query("SELECT w FROM WardEntity w WHERE w.name = :name AND w.district.code = :district_code")
    Optional<WardEntity> findByNameAndDistrict_Code(@Param("name")String name,
                                                        @Param("district_code")String districtCode);
}