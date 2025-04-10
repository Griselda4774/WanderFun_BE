package com.wanderfun.infrastructurelayer.repository;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.repository.FavouritePlaceRepository;
import com.wanderfun.domainlayer.model.users.FavouritePlace;
import com.wanderfun.infrastructurelayer.persistence.entity.FavouritePlaceEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.UserEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaFavouritePlaceRepository;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavouritePlaceRepositoryImpl extends BaseRepositoryImpl<FavouritePlace, FavouritePlaceEntity, Long> implements FavouritePlaceRepository {
    private final JpaFavouritePlaceRepository jpaFavouritePlaceRepository;
    private final JpaUserRepository jpaUserRepository;

    @Autowired
    public FavouritePlaceRepositoryImpl(JpaFavouritePlaceRepository jpaFavouritePlaceRepository, ObjectMapper objectMapper, JpaUserRepository jpaUserRepository) {
        super(jpaFavouritePlaceRepository, objectMapper, FavouritePlace.class, FavouritePlaceEntity.class);
        this.jpaFavouritePlaceRepository = jpaFavouritePlaceRepository;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public FavouritePlace save(FavouritePlace FavouritePlace) {
        FavouritePlaceEntity FavouritePlaceEntity = objectMapper.map(FavouritePlace, FavouritePlaceEntity.class);

        UserEntity userEntity = jpaUserRepository.findById(FavouritePlace.getUserId()).get();
        FavouritePlaceEntity.setUser(userEntity);

        FavouritePlaceEntity savedFavouritePlaceEntity = jpaFavouritePlaceRepository.save(FavouritePlaceEntity);
        return objectMapper.map(savedFavouritePlaceEntity, FavouritePlace.class);
    }

    @Override
    public List<FavouritePlace> findAllByUser_Id(Long userId) {
        List<FavouritePlace> FavouritePlaces = objectMapper.mapList(jpaFavouritePlaceRepository.findAllByUser_Id(userId), FavouritePlace.class);
        return FavouritePlaces;
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        jpaFavouritePlaceRepository.deleteByIds(ids);
    }
}
