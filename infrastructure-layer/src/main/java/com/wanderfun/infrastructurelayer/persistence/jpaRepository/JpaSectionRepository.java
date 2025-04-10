package com.wanderfun.infrastructurelayer.persistence.jpaRepository;

import com.wanderfun.domainlayer.model.places.Section;
import com.wanderfun.infrastructurelayer.persistence.entity.SectionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaSectionRepository extends JpaBaseRepository<SectionEntity, Long>{
    List<Section> findAllByPlaceId(Long placeId);
}
