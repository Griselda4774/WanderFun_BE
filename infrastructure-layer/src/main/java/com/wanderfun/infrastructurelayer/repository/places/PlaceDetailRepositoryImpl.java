package com.wanderfun.infrastructurelayer.repository.places;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.places.PlaceDetail;
import com.wanderfun.domainlayer.repository.place.PlaceDetailRepository;
import com.wanderfun.infrastructurelayer.persistence.entity.places.PlaceDetailEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.places.PlaceEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.places.SectionEntity;
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

    @Autowired
    public PlaceDetailRepositoryImpl(JpaPlaceDetailRepository jpaPlaceDetailRepository, ObjectMapper objectMapper) {
        super(jpaPlaceDetailRepository, objectMapper, PlaceDetail.class, PlaceDetailEntity.class);
        this.jpaPlaceDetailRepository = jpaPlaceDetailRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public PlaceDetail save(PlaceDetail placeDetail) {
        PlaceDetailEntity placeDetailEntity = objectMapper.map(placeDetail, PlaceDetailEntity.class);

        if (placeDetail.getPlaceId() != null) {
            PlaceEntity placeEntity = new PlaceEntity();
            placeEntity.setId(placeDetail.getPlaceId());
            placeDetailEntity.setPlace(placeEntity);
        }

        if (placeDetail.getSectionList() != null) {
            if (!placeDetail.getSectionList().isEmpty()) {
                List<SectionEntity> sectionEntityList = objectMapper.mapList(placeDetail.getSectionList(), SectionEntity.class);
                sectionEntityList.forEach(sectionEntity -> sectionEntity.setPlaceDetail(placeDetailEntity));
                placeDetailEntity.setSectionList(sectionEntityList);
            }
        }

        PlaceDetailEntity savedPlaceDetailEntity = jpaPlaceDetailRepository.save(placeDetailEntity);
        return objectMapper.map(savedPlaceDetailEntity, PlaceDetail.class);
    }

    @Override
    public Optional<PlaceDetail> findByPlaceId(Long placeId) {
        return jpaPlaceDetailRepository.findByPlace_Id(placeId)
                .map(placeDetailEntity -> objectMapper.map(placeDetailEntity, PlaceDetail.class));
    }
}
