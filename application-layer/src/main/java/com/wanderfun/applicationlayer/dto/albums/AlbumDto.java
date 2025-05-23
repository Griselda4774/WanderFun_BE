package com.wanderfun.applicationlayer.dto.albums;

import com.wanderfun.applicationlayer.dto.images.ImageDto;
import com.wanderfun.applicationlayer.dto.places.MiniPlaceDto;

import java.time.LocalTime;
import java.util.List;

public class AlbumDto {
    private Long id;
    private String name;
    private String description;
    private ImageDto coverImage;
    private List<AlbumImageDto> albumImages;
    private LocalTime createdAt;
    private LocalTime updatedAt;
    private MiniPlaceDto place;
    private Long userId;

    public AlbumDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageDto getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(ImageDto coverImage) {
        this.coverImage = coverImage;
    }

    public List<AlbumImageDto> getAlbumImages() {
        return albumImages;
    }

    public void setAlbumImages(List<AlbumImageDto> albumImages) {
        this.albumImages = albumImages;
    }

    public LocalTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public MiniPlaceDto getPlace() {
        return place;
    }

    public void setPlace(MiniPlaceDto place) {
        this.place = place;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
