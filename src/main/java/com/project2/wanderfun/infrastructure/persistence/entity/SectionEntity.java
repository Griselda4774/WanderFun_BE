package com.project2.wanderfun.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "section")
public class SectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "placeId")
    private PlaceEntity place;
}