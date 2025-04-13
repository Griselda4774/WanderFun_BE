package com.wanderfun.infrastructurelayer.persistence.entity.images;

public class Image {
    private Long id;
    private String imageUrl;
    private String imagePublicId;
    private ImageTargetType targetType;
    private Long targetId;

    public Image() {
    }
}
