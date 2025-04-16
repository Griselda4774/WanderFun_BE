//package com.wanderfun.infrastructurelayer.persistence.entity.trips;
//
//import com.wanderfun.domainlayer.model.places.Place;
//import com.wanderfun.domainlayer.model.trips.Trip;
//import jakarta.persistence.*;
//
//import java.util.Date;
//
//@Entity
//@Table(name = "trip_places")
//public class TripPlaceEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "place_id", nullable = false)
//    private Place place;
//
//    @ManyToOne
//    @JoinColumn(name = "trip_id", nullable = false)
//    private Trip trip;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "start_time", nullable = false)
//    private Date startTime;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "end_time", nullable = false)
//    private Date endTime;
//
//    @Column(name = "place_note", columnDefinition = "TEXT")
//    private String placeNote;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Place getPlace() {
//        return place;
//    }
//
//    public void setPlace(Place place) {
//        this.place = place;
//    }
//
//    public Trip getTrip() {
//        return trip;
//    }
//
//    public void setTrip(Trip trip) {
//        this.trip = trip;
//    }
//
//    public Date getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(Date startTime) {
//        this.startTime = startTime;
//    }
//
//    public Date getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(Date endTime) {
//        this.endTime = endTime;
//    }
//
//    public String getPlaceNote() {
//        return placeNote;
//    }
//
//    public void setPlaceNote(String placeNote) {
//        this.placeNote = placeNote;
//    }
//}
