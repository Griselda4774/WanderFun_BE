package com.project2.wanderfun.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "feedback_image")
public class FeedbackImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "feedbackId")
    private FeedbackEntity feedback;

    private String imageUrl;
}