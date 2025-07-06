package com.wanderfun.infrastructurelayer.persistence.jpaRepository.favoriteplaces;

import com.wanderfun.infrastructurelayer.persistence.entity.favoriteplace.FavoritePlaceEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.places.PlaceEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaFavoritePlaceRepository extends JpaBaseRepository<FavoritePlaceEntity, Long> {
    @Query("SELECT f.place FROM FavoritePlaceEntity f WHERE f.user.id = :user_id")
    List<PlaceEntity> findAllByUserId(@Param("user_id") Long userId);

    @Query("SELECT f FROM FavoritePlaceEntity f WHERE f.user.id = :user_id AND f.place.id = :place_id")
    Optional<FavoritePlaceEntity> findByUserIdAndPlaceId(@Param("user_id") Long userId,
                                                         @Param("place_id") Long placeId);
}