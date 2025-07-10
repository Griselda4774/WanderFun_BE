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
    @Query("""
        SELECT DISTINCT p FROM ProvinceEntity p
        WHERE p.name LIKE CONCAT('%', :name, '%')
        OR p.fullName LIKE CONCAT('%', :name, '%')
        OR p.nameEn LIKE CONCAT('%', :name, '%')
        OR p.fullNameEn LIKE CONCAT('%', :name, '%')
        ORDER BY
        CASE
        WHEN p.name LIKE CONCAT('%', :name, '%') THEN 0
        WHEN p.nameEn LIKE CONCAT('%', :name, '%') THEN 1
        ELSE 2
        END,
        p.name ASC
    """)
    List<ProvinceEntity> findAllByNameContaining(@Param("name") String name);

    @Query(" SELECT p FROM ProvinceEntity p WHERE p.name = :name")
    Optional<ProvinceEntity> findByName(@Param("name")String name);
}
