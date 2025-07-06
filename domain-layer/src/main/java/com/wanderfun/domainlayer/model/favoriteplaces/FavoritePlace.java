package com.wanderfun.domainlayer.model.favoriteplaces;

import com.wanderfun.domainlayer.model.places.Place;

public class FavoritePlace {
    private Long id;
    private Place place;
    private Long userId;

    public FavoritePlace() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
