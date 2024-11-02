package com.project2.wanderfun.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "place")
public class PlaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal longitude;
    private BigDecimal latitude;
    private String address;
    private String name;
    private String iconUrl;
    private String coverImageUrl;
    private Integer checkInPoint;
    private Float checkInRange;
    private String category;
    private LocalDateTime timeOpen;
    private LocalDateTime timeClose;
    private String alternativeName;
    private String operator;
    private String link;

    @OneToMany(mappedBy = "place")
    private List<TripPlaceEntity> tripPlaces;

    @ManyToMany(mappedBy = "favoritePlaces")
    private List<UserEntity> usersWhoFavorited;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<SectionEntity> sections;

    @OneToMany(mappedBy = "place")
    private List<PlaceImageEntity> placeImages;

    @OneToMany(mappedBy = "place")
    private List<FeedbackEntity> feedbacks;
}