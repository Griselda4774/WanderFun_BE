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
}
