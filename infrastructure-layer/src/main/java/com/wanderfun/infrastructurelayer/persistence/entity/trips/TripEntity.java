package com.wanderfun.infrastructurelayer.persistence.entity.trips;

import com.wanderfun.domainlayer.model.trips.TripPlace;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trips")
public class TripEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripPlace> listTripPlaces;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<TripPlace> getListTripPlaces() {
        return listTripPlaces;
    }

    public void setListTripPlaces(List<TripPlace> listTripPlaces) {
        this.listTripPlaces = listTripPlaces;
    }
}
