package com.wanderfun.infrastructurelayer.repository.places;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.repository.place.PlaceRepository;
import com.wanderfun.domainlayer.model.places.Place;
import com.wanderfun.infrastructurelayer.persistence.entity.addresses.AddressEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.images.ImageEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.places.PlaceCategoryEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.places.PlaceEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.places.JpaPlaceRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlaceRepositoryImpl extends BaseRepositoryImpl<Place, PlaceEntity, Long> implements PlaceRepository {
    private final JpaPlaceRepository jpaPlaceRepository;

    @Autowired
    public PlaceRepositoryImpl(JpaPlaceRepository jpaPlaceRepository, ObjectMapper objectMapper) {
        super(jpaPlaceRepository, objectMapper, Place.class, PlaceEntity.class);
        this.jpaPlaceRepository = jpaPlaceRepository;
    }

    @Override
    public Place save(Place place) {
        PlaceEntity placeEntity = objectMapper.map(place, PlaceEntity.class);

        if (place.getAddress() != null) {
            if (placeEntity.getAddress().getId() != null) {
                AddressEntity addressEntity = new AddressEntity();
                addressEntity.setId(place.getAddress().getId());
                placeEntity.setAddress(addressEntity);
            }
        }

        if (place.getCategory() != null) {
            if (placeEntity.getCategory().getId() != null) {
                PlaceCategoryEntity placeCategoryEntity = new PlaceCategoryEntity();
                placeCategoryEntity.setId(place.getCategory().getId());
                placeEntity.setCategory(placeCategoryEntity);
            }
        }

        if (place.getCoverImage() != null) {
            if (placeEntity.getCoverImage().getId() != null) {
                ImageEntity imageEntity = new ImageEntity();
                imageEntity.setId(place.getCoverImage().getId());
                placeEntity.setCoverImage(imageEntity);
            }
        }

        PlaceEntity savedPlaceEntity = jpaPlaceRepository.save(placeEntity);
        return objectMapper.map(savedPlaceEntity, Place.class);
    }

    @Override
    public List<Place> saveAll(List<Place> places) {
        List<PlaceEntity> placeEntities = places.stream()
                .map((place) -> {
                    PlaceEntity placeEntity = objectMapper.map(place, PlaceEntity.class);
                    if (place.getAddress() != null) {
                        if (placeEntity.getAddress().getId() != null) {
                            AddressEntity addressEntity = new AddressEntity();
                            addressEntity.setId(place.getAddress().getId());
                            placeEntity.setAddress(addressEntity);
                        }
                    }

                    if (place.getCategory() != null) {
                        if (placeEntity.getCategory().getId() != null) {
                            PlaceCategoryEntity placeCategoryEntity = new PlaceCategoryEntity();
                            placeCategoryEntity.setId(place.getCategory().getId());
                            placeEntity.setCategory(placeCategoryEntity);
                        }
                    }

                    if (place.getCoverImage() != null) {
                        if (placeEntity.getCoverImage().getId() != null) {
                            ImageEntity imageEntity = new ImageEntity();
                            imageEntity.setId(place.getCoverImage().getId());
                            placeEntity.setCoverImage(imageEntity);
                        }
                    }
                    return placeEntity;
                }).toList();

        List<PlaceEntity> savedPlaceEntities = jpaPlaceRepository.saveAll(placeEntities);

        return savedPlaceEntities.stream()
                .map(placeEntity -> objectMapper.map(placeEntity, Place.class))
                .toList();
    }

    @Override
    public List<Place> findAllByNameContaining(String name) {
        return objectMapper.mapList(jpaPlaceRepository.findAllByNameContaining(name), Place.class);
    }

    @Override
    public Optional<Place> findByName(String name) {
        return jpaPlaceRepository.findByName(name)
                .map(entity -> objectMapper.map(entity, Place.class));
    }

    @Override
    public Optional<Place> findByLongitudeAndLatitude(double longitude, double latitude) {
        return jpaPlaceRepository.findByLongitudeAndLatitude(longitude, latitude)
                .map(entity -> objectMapper.map(entity, Place.class));
    }

    @Override
    public List<Place> findAllByProvinceName(String provinceName) {
        return objectMapper.mapList(jpaPlaceRepository.findByProvinceName(provinceName), Place.class);
    }
}
