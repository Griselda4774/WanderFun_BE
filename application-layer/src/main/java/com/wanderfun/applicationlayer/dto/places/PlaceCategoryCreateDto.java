package com.wanderfun.applicationlayer.dto.places;

import com.wanderfun.domainlayer.model.images.Image;

public class PlaceCategoryCreateDto {

    private String name;
    private String nameEn;
    private Image iconImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Image getIconImage() {
        return iconImage;
    }

    public void setIconImage(Image iconImage) {
        this.iconImage = iconImage;
    }
}
