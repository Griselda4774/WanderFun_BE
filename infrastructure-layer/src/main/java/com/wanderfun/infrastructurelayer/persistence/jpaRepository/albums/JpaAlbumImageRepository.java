package com.wanderfun.infrastructurelayer.persistence.jpaRepository.albums;

import com.wanderfun.infrastructurelayer.persistence.entity.albums.AlbumImageEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAlbumImageRepository extends JpaBaseRepository<AlbumImageEntity, Long> {
}
