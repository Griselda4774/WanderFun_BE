package com.wanderfun.domainlayer.model.statistics;

public class PlaceRanking {
    private String name;
    private String coverImageUrl;
    private int checkInCount;
    private int ranking;
    private Long placeId;

    public PlaceRanking() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public int getCheckInCount() {
        return checkInCount;
    }

    public void setCheckInCount(int checkInCount) {
        this.checkInCount = checkInCount;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }
}
