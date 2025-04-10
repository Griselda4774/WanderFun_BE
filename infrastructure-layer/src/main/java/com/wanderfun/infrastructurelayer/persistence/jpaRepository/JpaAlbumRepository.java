package com.wanderfun.infrastructurelayer.persistence.jpaRepository;

import com.wanderfun.infrastructurelayer.persistence.entity.AlbumEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaAlbumRepository extends JpaBaseRepository<AlbumEntity, Long> {
    Optional<AlbumEntity> findByName(String name);
    List<AlbumEntity> findAllByUser_Id(Long userId);
    void deleteAllByUser_Id(Long userId);
}
