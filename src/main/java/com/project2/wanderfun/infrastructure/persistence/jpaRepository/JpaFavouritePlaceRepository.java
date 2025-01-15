package com.project2.wanderfun.infrastructure.persistence.jpaRepository;

import com.project2.wanderfun.domain.model.FavouritePlace;
import com.project2.wanderfun.infrastructure.persistence.entity.FavouritePlaceEntity;
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