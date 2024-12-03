package com.project2.wanderfun.infrastructure.persistence.jpaRepository;

import com.project2.wanderfun.infrastructure.persistence.entity.PlaceEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaPlaceRepository extends JpaBaseRepository<PlaceEntity, Long>{
    List<PlaceEntity> findAllByNameContaining(String name);
    Optional<PlaceEntity> findByName(String name);
}
