package com.project2.wanderfun.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "feedback_image")
public class FeedbackImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedbackId")
    private FeedbackEntity feedback;

    private String imageUrl;

    public FeedbackImageEntity() {
    }

    public Long getId() {
        return id;
    }

    public FeedbackEntity getFeedback() {
        return feedback;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFeedback(FeedbackEntity feedback) {
        this.feedback = feedback;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}