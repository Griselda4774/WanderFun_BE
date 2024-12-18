package com.project2.wanderfun.application.dto.feedbackimage;

public class FeedbackImageDto {
    private Long id;
    private String imageUrl;
    private Long feedbackId;

    public FeedbackImageDto() {};

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

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }
}
