package com.wanderfun.applicationlayer.dto.posts;

import com.wanderfun.applicationlayer.dto.images.ImageDto;
import com.wanderfun.applicationlayer.dto.users.MiniUserDto;
import com.wanderfun.domainlayer.model.places.MiniPlaceDto;
import com.wanderfun.domainlayer.model.trips.Trip;

import java.time.LocalDateTime;

public class PostDto {
    private Long id;
    private MiniUserDto user;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private MiniPlaceDto place;
    private boolean isTripShare;
    private Trip trip;
    private ImageDto image;
    private int likeCount;
    private boolean isLiked;

    public PostDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MiniUserDto getUser() {
        return user;
    }

    public void setUser(MiniUserDto user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public MiniPlaceDto getPlace() {
        return place;
    }

    public void setPlace(MiniPlaceDto place) {
        this.place = place;
    }

    public boolean isTripShare() {
        return isTripShare;
    }

    public void setTripShare(boolean tripShare) {
        isTripShare = tripShare;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }

    public int getLike() {
        return likeCount;
    }

    public void setLike(int like) {
        this.likeCount = like;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
