package com.project2.wanderfun.application.dto.leaderboard;

public class LeaderboardPlaceDto {
    private Long id;
    private String name;
    private String coverImageUrl;
    private int checkInCount;
    private int rank;
    private Long placeId;


    public LeaderboardPlaceDto() {
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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Long getPlaceId() {
        return placeId;
    }
}
