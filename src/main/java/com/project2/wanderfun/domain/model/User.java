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

    public User() {
    }

    public User(String role, String password, String email, String lastName, String firstName, List<Album> albums, List<Trip> trips, List<String> favoritePlaceIds, int point, String phoneNumber, String gender, Date dateOfBirth, String avatarUrl) {
        super(role, password, email, lastName, firstName);
        this.albums = albums;
        this.trips = trips;
        this.favoritePlaceIds = favoritePlaceIds;
        this.point = point;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getPoint() {
        return point;
    }

    public List<String> getFavoritePlaceIds() {
        return favoritePlaceIds;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setFavoritePlaceIds(List<String> favoritePlaceIds) {
        this.favoritePlaceIds = favoritePlaceIds;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
