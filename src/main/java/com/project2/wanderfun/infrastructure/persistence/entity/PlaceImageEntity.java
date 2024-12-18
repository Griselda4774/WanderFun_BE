package com.project2.wanderfun.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "place_image")
public class PlaceImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "placeId")
    private PlaceEntity place;

    public PlaceImageEntity() {
    }

    public Long getId() {
        return id;
    }

    public PlaceEntity getPlace() {
        return place;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlace(PlaceEntity place) {
        this.place = place;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}