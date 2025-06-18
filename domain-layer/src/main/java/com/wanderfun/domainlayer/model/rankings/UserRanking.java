package com.wanderfun.domainlayer.model.rankings;

public class UserRanking {
    private Long id;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private int point;
    private int rank;
    private int checkInCount;
    private int placeCheckInCount;
    private Long userId;

    public UserRanking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getCheckInCount() {
        return checkInCount;
    }

    public void setCheckInCount(int checkInCount) {
        this.checkInCount = checkInCount;
    }

    public int getPlaceCheckInCount() {
        return placeCheckInCount;
    }

    public void setPlaceCheckInCount(int placeCheckInCount) {
        this.placeCheckInCount = placeCheckInCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
