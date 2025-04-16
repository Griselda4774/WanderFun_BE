package com.wanderfun.applicationlayer.service.place;

import com.wanderfun.applicationlayer.service.BaseService;
import com.wanderfun.domainlayer.model.places.PlaceCategory;

public interface PlaceCategoryService extends BaseService<PlaceCategory, Integer> {
    PlaceCategory findByName(String name);
    PlaceCategory findByNameEn(String nameEn);
    PlaceCategory findById(Integer id);
}
