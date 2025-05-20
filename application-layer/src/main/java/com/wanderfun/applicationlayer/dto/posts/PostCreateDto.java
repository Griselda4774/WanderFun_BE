package com.wanderfun.applicationlayer.dto.posts;

import com.wanderfun.applicationlayer.dto.images.ImageDto;

public class PostCreateDto {
    private String content;
    private Long placeId;
    private boolean isTripShare;
    private Long tripId;
    private ImageDto image;

    public PostCreateDto() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public boolean isTripShare() {
        return isTripShare;
    }

    public void setTripShare(boolean tripShare) {
        isTripShare = tripShare;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }
}
