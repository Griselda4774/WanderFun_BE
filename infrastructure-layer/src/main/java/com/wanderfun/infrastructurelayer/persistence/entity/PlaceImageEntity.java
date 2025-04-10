package com.wanderfun.infrastructurelayer.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "place_image")
public class PlaceImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;
    private String imagePublicId;
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

    public String getImagePublicId() {
        return imagePublicId;
    }

    public void setImagePublicId(String imagePublicId) {
        this.imagePublicId = imagePublicId;
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