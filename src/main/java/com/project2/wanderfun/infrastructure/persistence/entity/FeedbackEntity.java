package com.project2.wanderfun.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "feedback")
public class FeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String userAvatar;
    private int rating;
    private String comment;
    @OneToMany(mappedBy = "feedback", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedbackImageEntity> feedbackImages = new ArrayList<>();
    private Date time;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "placeId")
    private PlaceEntity place;

    public FeedbackEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<FeedbackImageEntity> getFeedbackImages() {
        return feedbackImages;
    }

    public void setFeedbackImages(List<FeedbackImageEntity> feedbackImages) {
        this.feedbackImages = feedbackImages;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public PlaceEntity getPlace() {
        return place;
    }

    public void setPlace(PlaceEntity place) {
        this.place = place;
    }
}