package com.wanderfun.applicationlayer.dto.trips;

import com.wanderfun.applicationlayer.dto.users.MiniUserDto;

import java.time.LocalDate;

public class MiniTripDto {
    private Long id;
    private String name;
    private LocalDate startTime;
    private LocalDate endTime;
    private MiniUserDto user;

    public MiniTripDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public MiniUserDto getUser() {
        return user;
    }

    public void setUser(MiniUserDto user) {
        this.user = user;
    }
}
