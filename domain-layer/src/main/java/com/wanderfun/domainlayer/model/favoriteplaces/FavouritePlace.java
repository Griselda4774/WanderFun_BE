package com.wanderfun.domainlayer.model.favoriteplaces;

public class FavouritePlace {
    private Long id;
    private Long placeId;
    private Long userId;

    public FavouritePlace() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
