package com.project2.wanderfun.infrastructure.persistence.jpaRepository;

import com.project2.wanderfun.infrastructure.persistence.entity.AlbumEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAlbumRepository extends JpaBaseRepository<AlbumEntity, Long> {
}
