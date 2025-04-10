package com.wanderfun.infrastructurelayer.persistence.entity.places;

import com.wanderfun.infrastructurelayer.persistence.entity.images.Image;

public class Section {
    private Long id;
    private String title;
    private String content;
    private Image image;
    private Long placeDetailId;

    public Section() {
    }
}
