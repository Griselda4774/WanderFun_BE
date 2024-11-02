package com.project2.wanderfun.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "trip_place")
public class TripPlaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tripId")
    private TripEntity trip;

    @ManyToOne
    @JoinColumn(name = "placeId")
    private PlaceEntity place;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}