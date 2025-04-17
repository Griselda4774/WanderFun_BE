package com.wanderfun.infrastructurelayer.persistence.jpaRepository.addresses;

import com.wanderfun.infrastructurelayer.persistence.entity.addresses.ProvinceEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaProvinceRepository extends JpaBaseRepository<ProvinceEntity, String> {
    @Query("SELECT p FROM ProvinceEntity p WHERE p.name LIKE %:name%")
    List<ProvinceEntity> findAllByNameContaining(@Param("name") String name);

    @Query(" SELECT p FROM ProvinceEntity p WHERE p.name = :name")
    Optional<ProvinceEntity> findByName(@Param("name")String name);
}
