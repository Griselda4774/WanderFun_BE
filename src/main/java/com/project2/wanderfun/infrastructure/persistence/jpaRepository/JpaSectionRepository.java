package com.project2.wanderfun.infrastructure.persistence.jpaRepository;

import com.project2.wanderfun.infrastructure.persistence.entity.SectionEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSectionRepository extends JpaBaseRepository<SectionEntity, Long>{
}
