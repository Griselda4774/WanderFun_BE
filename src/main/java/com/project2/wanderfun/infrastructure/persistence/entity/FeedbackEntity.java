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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "placeId")
    private PlaceEntity place;

    @OneToMany(mappedBy = "feedback")
    private List<FeedbackImageEntity> feedbackImages;

    public FeedbackEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public Float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getTimeArrived() {
        return timeArrived;
    }

    public PlaceEntity getPlace() {
        return place;
    }

    public List<FeedbackImageEntity> getFeedbackImages() {
        return feedbackImages;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTimeArrived(LocalDateTime timeArrived) {
        this.timeArrived = timeArrived;
    }

    public void setPlace(PlaceEntity place) {
        this.place = place;
    }

    public void setFeedbackImages(List<FeedbackImageEntity> feedbackImages) {
        this.feedbackImages = feedbackImages;
    }
}