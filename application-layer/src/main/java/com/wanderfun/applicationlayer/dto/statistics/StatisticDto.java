package com.wanderfun.applicationlayer.dto.statistics;

import com.wanderfun.applicationlayer.dto.auths.AccountDto;
import com.wanderfun.applicationlayer.dto.places.PlaceDto;

import java.util.List;

public class StatisticDto {
    private Long totalUsers;
    private Long totalPlaces;
    private Long totalCreatedAccountsToday;
    private Long TotalCheckInAllTime;
    private Long totalCheckInToday;
    private List<AccountDto> AccountsList ;
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

    public Long getTotalCreatedAccountsToday() {
        return totalCreatedAccountsToday;
    }

    public void setTotalCreatedAccountsToday(Long totalCreatedAccountsToday) {
        this.totalCreatedAccountsToday = totalCreatedAccountsToday;
    }

    public List<AccountDto> getAccountsList() {
        return AccountsList;
    }

    public void setAccountsList(List<AccountDto> accountsList) {
        AccountsList = accountsList;
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
