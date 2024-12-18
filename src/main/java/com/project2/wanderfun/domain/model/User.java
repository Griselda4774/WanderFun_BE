package com.project2.wanderfun.domain.model;

import com.project2.wanderfun.domain.model.enums.UserRole;

import java.util.Date;
import java.util.List;

public class User extends BaseUser{
    private String avatarUrl;
    private Date dateOfBirth;
    private String gender;
    private String phoneNumber;
    private int point;
    private boolean isCreatedProfile;
    private List<FavouritePlace> favouritePlaces;
    private List<Trip> trips;
    private List<Album> albums;
    public User() {
        this.setCreatedProfile(false);
        this.setPoint(0);
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public boolean isCreatedProfile() {
        return isCreatedProfile;
    }

    public void setCreatedProfile(boolean createdProfile) {
        isCreatedProfile = createdProfile;
    }

    public List<FavouritePlace> getFavouritePlaces() {
        return favouritePlaces;
    }

    public void setFavouritePlaces(List<FavouritePlace> favouritePlaces) {
        this.favouritePlaces = favouritePlaces;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
