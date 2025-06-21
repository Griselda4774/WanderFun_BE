package com.wanderfun.applicationlayer.dto.statistics;

public class UserRankingDto {
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private int point;
    private int ranking;
    private int checkInCount;
    private int placeCheckInCount;
    private Long userId;

    public UserRankingDto() {
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

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
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
