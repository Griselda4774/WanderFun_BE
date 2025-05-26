package com.wanderfun.applicationlayer.dto.albums;

public class AlbumCreateDto {
    private String name;
    private String description;
    private Long placeId;

    public AlbumCreateDto() {
    }

    public AlbumCreateDto(String name, String description, Long placeId) {
        this.name = name;
        this.description = description;
        this.placeId = placeId;
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

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }
}
