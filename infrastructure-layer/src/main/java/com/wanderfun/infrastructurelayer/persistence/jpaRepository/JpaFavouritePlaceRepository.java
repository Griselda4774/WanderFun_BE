package com.wanderfun.infrastructurelayer.persistence.jpaRepository;

import com.wanderfun.infrastructurelayer.persistence.entity.FavouritePlaceEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaFavouritePlaceRepository extends JpaBaseRepository<FavouritePlaceEntity, Long> {
    List<FavouritePlaceEntity> findAllByUser_Id(Long userId);
    @Modifying
    @Query("DELETE FROM FavouritePlaceEntity e WHERE e.id IN :ids")
    void deleteByIds(@Param("ids") List<Long> ids);
}