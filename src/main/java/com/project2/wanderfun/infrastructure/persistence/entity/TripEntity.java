package com.project2.wanderfun.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "trip")
public class TripEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // Relationships
    @OneToMany(mappedBy = "trip")
    private List<TripPlaceEntity> tripPlaces;
}