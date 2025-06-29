package com.wanderfun.applicationlayer.dto.statistics;

import java.util.List;

public class StatisticDto {
    private Long totalUsers;
    private Long totalPlaces;
    private Long TotalCheckInsAllTime;
    private Long totalCheckInsToday;
    private List<PlaceRankingDto> TopCheckInsPlaces;
    private List<UserRankingDto> TopCheckInsUsers;

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

    public Long getTotalCheckInsAllTime() {
        return TotalCheckInsAllTime;
    }

    public void setTotalCheckInsAllTime(Long totalCheckInsAllTime) {
        TotalCheckInsAllTime = totalCheckInsAllTime;
    }

    public Long getTotalCheckInsToday() {
        return totalCheckInsToday;
    }

    public void setTotalCheckInsToday(Long totalCheckInsToday) {
        this.totalCheckInsToday = totalCheckInsToday;
    }

    public List<PlaceRankingDto> getTopCheckInsPlaces() {
        return TopCheckInsPlaces;
    }

    public void setTopCheckInsPlaces(List<PlaceRankingDto> topCheckInsPlaces) {
        TopCheckInsPlaces = topCheckInsPlaces;
    }

    public List<UserRankingDto> getTopCheckInsUsers() {
        return TopCheckInsUsers;
    }

    public void setTopCheckInsUsers(List<UserRankingDto> topCheckInsUsers) {
        TopCheckInsUsers = topCheckInsUsers;
    }
}
