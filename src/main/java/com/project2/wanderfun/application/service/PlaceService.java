package com.project2.wanderfun.application.service;

import com.project2.wanderfun.domain.model.Place;

import java.util.List;

public interface PlaceService extends BaseService<Place>{
    public List<Place> findAllByNameContaining(String name);
    public Place findByName(String name);
}
