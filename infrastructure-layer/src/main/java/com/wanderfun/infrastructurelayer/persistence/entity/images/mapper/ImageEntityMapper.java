package com.wanderfun.infrastructurelayer.persistence.entity.images.mapper;

import com.wanderfun.domainlayer.model.images.Image;
import com.wanderfun.infrastructurelayer.persistence.entity.images.ImageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageEntityMapper {
    ImageEntity fromModel(Image image);
    Image toModel(ImageEntity imageEntity);
}
