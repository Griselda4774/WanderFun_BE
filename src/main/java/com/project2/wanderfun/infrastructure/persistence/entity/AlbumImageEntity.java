package com.project2.wanderfun.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "album_image")
public class AlbumImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "albumId")
    private AlbumEntity album;

    private String imageUrl;

    public AlbumImageEntity() {
    }

    public Long getId() {
        return id;
    }

    public AlbumEntity getAlbum() {
        return album;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAlbum(AlbumEntity album) {
        this.album = album;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}