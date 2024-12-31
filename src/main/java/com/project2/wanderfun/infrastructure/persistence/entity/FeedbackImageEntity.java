package com.project2.wanderfun.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "feedback_image")
public class FeedbackImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;
    private String imagePublicId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedbackId")
    private FeedbackEntity feedback;

    public FeedbackImageEntity() {
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

    public FeedbackEntity getFeedback() {
        return feedback;
    }

    public void setFeedback(FeedbackEntity feedback) {
        this.feedback = feedback;
    }
}