package com.wanderfun.domainlayer.model.posts;

import com.wanderfun.domainlayer.model.places.Place;
import com.wanderfun.domainlayer.model.users.User;
import com.wanderfun.domainlayer.model.trips.Trip;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Post {
    private Long id;
    private User user;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Place place;
    private boolean isTripShare;
    private Trip trip;
    private int like;

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
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

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
