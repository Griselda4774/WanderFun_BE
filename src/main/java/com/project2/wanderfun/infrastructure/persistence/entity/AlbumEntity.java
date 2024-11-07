package com.project2.wanderfun.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "album")
public class AlbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    private String name;
    private String placeId;
    private String description;

    // Relationships
    @OneToMany(mappedBy = "album")
    private List<AlbumImageEntity> albumImages;

    public AlbumEntity() {
    }

    public Long getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getPlaceId() {
        return placeId;
    }

    public String getDescription() {
        return description;
    }

    public List<AlbumImageEntity> getAlbumImages() {
        return albumImages;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAlbumImages(List<AlbumImageEntity> albumImages) {
        this.albumImages = albumImages;
    }
}
