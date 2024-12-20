package com.project2.wanderfun.application.dto.album;

import com.project2.wanderfun.application.dto.albumimage.AlbumImageDto;
import com.project2.wanderfun.domain.model.AlbumImage;
import jakarta.validation.constraints.Digits;

import java.math.BigDecimal;
import java.util.List;

public class AlbumDto {
    private Long id;
    private String name;
    private String description;
    private Long placeId;
    @Digits(integer = 3, fraction = 6, message = "Longitude must have at most 3 integer digits and 6 fraction digits")
    private BigDecimal placeLongitude;
    @Digits(integer = 2, fraction = 6, message = "Latitude must have at most 2 integer digits and 6 fraction digits")
    private BigDecimal placeLatitude;
    private String placeName;
    private String placeCoverImageUrl;
    private List<AlbumImageDto> albumImages;

    public AlbumDto() {};

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

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public BigDecimal getPlaceLongitude() {
        return placeLongitude;
    }

    public void setPlaceLongitude(BigDecimal placeLongitude) {
        this.placeLongitude = placeLongitude;
    }

    public BigDecimal getPlaceLatitude() {
        return placeLatitude;
    }

    public void setPlaceLatitude(BigDecimal placeLatitude) {
        this.placeLatitude = placeLatitude;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceCoverImageUrl() {
        return placeCoverImageUrl;
    }

    public void setPlaceCoverImageUrl(String placeCoverImageUrl) {
        this.placeCoverImageUrl = placeCoverImageUrl;
    }

    public List<AlbumImageDto> getAlbumImages() {
        return albumImages;
    }

    public void setAlbumImages(List<AlbumImageDto> albumImages) {
        this.albumImages = albumImages;
    }
}
