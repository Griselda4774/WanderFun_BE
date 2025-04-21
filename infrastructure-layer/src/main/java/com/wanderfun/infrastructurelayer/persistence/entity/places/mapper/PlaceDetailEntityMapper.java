package com.wanderfun.infrastructurelayer.persistence.entity.places.mapper;

import com.wanderfun.domainlayer.model.places.PlaceDetail;
import com.wanderfun.infrastructurelayer.persistence.entity.places.PlaceDetailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SectionEntityMapper.class})
public interface PlaceDetailEntityMapper {
    @Mapping(source = "sectionList", target = "sectionList")
    PlaceDetailEntity toModel(PlaceDetail placeDetail);

    @Mapping(source = "sectionList", target = "sectionList")
    PlaceDetail toModel(PlaceDetailEntity placeDetailEntity);
}
