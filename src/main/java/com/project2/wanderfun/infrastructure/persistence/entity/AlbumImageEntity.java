package com.project2.wanderfun.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "album_image")
public class AlbumImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;
    private String imagePublicId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "albumId")
    private AlbumEntity album;

    public AlbumImageEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImagePublicId() {
        return imagePublicId;
    }

    public void setImagePublicId(String imagePublicId) {
        this.imagePublicId = imagePublicId;
    }

    public AlbumEntity getAlbum() {
        return album;
    }

    public void setAlbum(AlbumEntity album) {
        this.album = album;
    }
}