package com.project2.wanderfun.infrastructure.persistence.jpaRepository;

import com.project2.wanderfun.infrastructure.persistence.entity.PlaceEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaPlaceRepository extends JpaBaseRepository<PlaceEntity, Long>{
    List<PlaceEntity> findByNameContaining(String name);
}
