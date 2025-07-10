package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.applicationlayer.dto.addresses.ProvinceDto;
import com.wanderfun.applicationlayer.dto.places.MiniPlaceDto;

import java.util.List;

public interface AutoCompleteSearchUsecase {
    List<MiniPlaceDto> autoCompleteSearchPlace(String query);
    List<ProvinceDto> autoCompleteSearchProvince(String query);
}
