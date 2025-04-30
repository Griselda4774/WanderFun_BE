package com.wanderfun.infrastructurelayer.persistence.entity.posts;

import com.wanderfun.infrastructurelayer.persistence.entity.places.PlaceEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.trips.TripEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.users.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
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
    private LocalDateTime createAt;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private PlaceEntity place;

    @Column(name = "is_trip_share", nullable = false)
    private Boolean isTripShare = false;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private TripEntity trip;

    public PostEntity() {}

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
}

