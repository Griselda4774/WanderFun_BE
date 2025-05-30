package com.wanderfun.applicationlayer.dto.albums;

import com.wanderfun.applicationlayer.dto.images.ImageDto;
import com.wanderfun.applicationlayer.dto.places.MiniPlaceDto;

import java.time.LocalDateTime;
import java.util.List;

public class AlbumDto {
    private Long id;
    private String name;
    private String description;
    private ImageDto coverImage;
    private List<AlbumImageDto> albumImageList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
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

    public List<AlbumImageDto> getAlbumImageList() {
        return albumImageList;
    }

    public void setAlbumImageList(List<AlbumImageDto> albumImageList) {
        this.albumImageList = albumImageList;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
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
