package com.project2.wanderfun.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private Date dateOfBirth;
    private String gender;
    private String phoneNumber;
    private Integer point;

    @OneToMany(mappedBy = "user")
    private List<AlbumEntity> albums;

    @OneToMany(mappedBy = "user")
    private List<TripEntity> trips;

    @ManyToMany
    @JoinTable(
            name = "favorite_place",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "placeId")
    )
    private List<PlaceEntity> favoritePlaces;

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public Integer getPoint() {
        return point;
    }

    public List<AlbumEntity> getAlbums() {
        return albums;
    }

    public List<TripEntity> getTrips() {
        return trips;
    }

    public List<PlaceEntity> getFavoritePlaces() {
        return favoritePlaces;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void setPoint(Integer point) {
        this.point = point;
    }

    public void setAlbums(List<AlbumEntity> albums) {
        this.albums = albums;
    }

    public void setTrips(List<TripEntity> trips) {
        this.trips = trips;
    }

    public void setFavoritePlaces(List<PlaceEntity> favoritePlaces) {
        this.favoritePlaces = favoritePlaces;
    }
}
