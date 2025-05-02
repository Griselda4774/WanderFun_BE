package com.wanderfun.infrastructurelayer.repository.places;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.places.PlaceDetail;
import com.wanderfun.domainlayer.repository.place.PlaceDetailRepository;
import com.wanderfun.infrastructurelayer.persistence.entity.places.PlaceDetailEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.places.PlaceEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.places.SectionEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.places.mapper.PlaceDetailEntityMapper;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.places.JpaPlaceDetailRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlaceDetailRepositoryImpl extends BaseRepositoryImpl<PlaceDetail, PlaceDetailEntity, Long> implements PlaceDetailRepository {
    private final JpaPlaceDetailRepository jpaPlaceDetailRepository;
    private final ObjectMapper objectMapper;
    private final PlaceDetailEntityMapper placeDetailEntityMapper;

    @Autowired
    public PlaceDetailRepositoryImpl(JpaPlaceDetailRepository jpaPlaceDetailRepository, ObjectMapper objectMapper, PlaceDetailEntityMapper placeDetailEntityMapper) {
        super(jpaPlaceDetailRepository, objectMapper, PlaceDetail.class, PlaceDetailEntity.class);
        this.jpaPlaceDetailRepository = jpaPlaceDetailRepository;
        this.objectMapper = objectMapper;
        this.placeDetailEntityMapper = placeDetailEntityMapper;
    }

    @Override
    public PlaceDetail save(PlaceDetail placeDetail) {
        PlaceDetailEntity placeDetailEntity = objectMapper.map(placeDetail, PlaceDetailEntity.class);

        if (placeDetail.getPlaceId() != null) {
            placeDetailEntity.setPlace(new PlaceEntity());
            placeDetailEntity.getPlace().setId(placeDetail.getPlaceId());
        }

        if (placeDetail.getSectionList() != null && !placeDetail.getSectionList().isEmpty()) {
            List<SectionEntity> sectionEntityList = objectMapper.mapList(placeDetail.getSectionList(), SectionEntity.class);
            sectionEntityList.forEach(sectionEntity -> sectionEntity.setPlaceDetail(placeDetailEntity));
            placeDetailEntity.setSectionList(sectionEntityList);
        }

        PlaceDetailEntity savedPlaceDetailEntity = jpaPlaceDetailRepository.save(placeDetailEntity);
        return objectMapper.map(savedPlaceDetailEntity, PlaceDetail.class);
    }

    @Override
    public Optional<PlaceDetail> findByPlaceId(Long placeId) {
        return jpaPlaceDetailRepository.findByPlace_Id(placeId)
                .map(placeDetailEntityMapper::toModel);
    }
}
