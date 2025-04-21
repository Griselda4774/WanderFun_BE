package com.wanderfun.infrastructurelayer.persistence.entity.places.mapper;

import com.wanderfun.domainlayer.model.places.Section;
import com.wanderfun.infrastructurelayer.persistence.entity.images.mapper.ImageEntityMapper;
import com.wanderfun.infrastructurelayer.persistence.entity.places.SectionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ImageEntityMapper.class})
public interface SectionEntityMapper {
    SectionEntity fromModel(Section section);
    Section toModel(SectionEntity sectionEntity);
}
