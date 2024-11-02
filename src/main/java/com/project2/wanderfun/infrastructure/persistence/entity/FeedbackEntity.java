package com.project2.wanderfun.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "feedback")
public class FeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private Float rating;
    private String comment;
    private LocalDateTime timeArrived;

    @ManyToOne
    @JoinColumn(name = "placeId")
    private PlaceEntity place;

    @OneToMany(mappedBy = "feedback")
    private List<FeedbackImageEntity> feedbackImages;
}