package com.wanderfun.infrastructurelayer.persistence.entity.posts;

import com.wanderfun.infrastructurelayer.persistence.entity.images.ImageEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.places.PlaceEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.trips.TripEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.users.UserEntity;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "create_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createAt;

    @Column(name = "update_at", nullable = false)
    @LastModifiedDate
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private PlaceEntity place;

    @Column(name = "is_trip_share", nullable = false)
    private Boolean isTripShare = false;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private TripEntity trip;

    @OneToOne
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    @Transient
    private Long likeCount;

    public PostEntity() {}

    public PostEntity(Long id, UserEntity user, String content, LocalDateTime createAt,
                      LocalDateTime updateAt, PlaceEntity place, Boolean isTripShare,
                      TripEntity trip, ImageEntity image, Long likeCount) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.place = place;
        this.isTripShare = isTripShare;
        this.trip = trip;
        this.image = image;
        this.likeCount = likeCount != null ? likeCount : 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public PlaceEntity getPlace() {
        return place;
    }

    public void setPlace(PlaceEntity place) {
        this.place = place;
    }

    public Boolean getTripShare() {
        return isTripShare;
    }

    public void setTripShare(Boolean tripShare) {
        isTripShare = tripShare;
    }

    public TripEntity getTrip() {
        return trip;
    }

    public void setTrip(TripEntity trip) {
        this.trip = trip;
    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }
}

