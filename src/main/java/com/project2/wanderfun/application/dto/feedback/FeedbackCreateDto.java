package com.project2.wanderfun.application.dto.feedback;

import com.project2.wanderfun.application.dto.feedbackimage.FeedbackImageCreateDto;

import java.util.List;

public class FeedbackCreateDto {
    private int rating;
    private String comment;
    private List<FeedbackImageCreateDto> feedbackImages;

    public FeedbackCreateDto() {
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

    public List<FeedbackImageCreateDto> getFeedbackImages() {
        return feedbackImages;
    }

    public void setFeedbackImages(List<FeedbackImageCreateDto> feedbackImages) {
        this.feedbackImages = feedbackImages;
    }
}
