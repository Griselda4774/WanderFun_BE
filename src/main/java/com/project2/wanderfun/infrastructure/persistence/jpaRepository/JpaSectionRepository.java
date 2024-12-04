package com.project2.wanderfun.infrastructure.persistence.jpaRepository;

import com.project2.wanderfun.domain.model.Section;
import com.project2.wanderfun.infrastructure.persistence.entity.SectionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaSectionRepository extends JpaBaseRepository<SectionEntity, Long>{
    List<Section> findAllByPlaceId(Long placeId);
}
