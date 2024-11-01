package com.project2.wanderfun.domain.model;

import java.util.Date;
import java.util.List;

public class User extends BaseUser{
    private String avatarUrl;
    private Date dateOfBirth;
    private String gender;
    private String phoneNumber;
    private int point;
    private List<String> favoritePlaceIds;
    private List<Trip> trips;
    private List<Album> albums;
}
