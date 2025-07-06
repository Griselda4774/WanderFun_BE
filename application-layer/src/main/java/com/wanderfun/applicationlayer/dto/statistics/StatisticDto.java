package com.wanderfun.applicationlayer.dto.statistics;

import java.util.List;

public class StatisticDto {
    private Long totalUsers;
    private Long totalPlaces;
    private Long TotalCheckInAllTime;
    private Long totalCheckInToday;
    private List<PlaceRankingDto> TopCheckInPlaces;
    private List<UserRankingDto> TopCheckInUsers;

    public StatisticDto() {}

    public Long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(Long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public Long getTotalPlaces() {
        return totalPlaces;
    }

    public void setTotalPlaces(Long totalPlaces) {
        this.totalPlaces = totalPlaces;
    }

    public Long getTotalCheckInAllTime() {
        return TotalCheckInAllTime;
    }

    public void setTotalCheckInAllTime(Long totalCheckInAllTime) {
        TotalCheckInAllTime = totalCheckInAllTime;
    }

    public Long getTotalCheckInToday() {
        return totalCheckInToday;
    }

    public void setTotalCheckInToday(Long totalCheckInToday) {
        this.totalCheckInToday = totalCheckInToday;
    }

    public List<PlaceRankingDto> getTopCheckInPlaces() {
        return TopCheckInPlaces;
    }

    public void setTopCheckInPlaces(List<PlaceRankingDto> topCheckInPlaces) {
        TopCheckInPlaces = topCheckInPlaces;
    }

    public List<UserRankingDto> getTopCheckInUsers() {
        return TopCheckInUsers;
    }

    public void setTopCheckInUsers(List<UserRankingDto> topCheckInUsers) {
        TopCheckInUsers = topCheckInUsers;
    }
}
