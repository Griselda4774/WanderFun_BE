package com.project2.wanderfun.application.dto.section;

public class SectionCreateDto {
    private String title;
    private String content;
    private String imageUrl;

    public SectionCreateDto() {};

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}