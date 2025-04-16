package com.wanderfun.infrastructurelayer.persistence.entity.places;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "place_details")
public class PlaceDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false)
    private PlaceEntity place;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "check_in_point")
    private Integer checkInPoint = 0;

    @Column(name = "check_in_range_meter")
    private Float checkInRangeMeter = 100f;

    @Column(name = "time_open")
    private LocalTime timeOpen;

    @Column(name = "time_close")
    private LocalTime timeClose;

    @Column(name = "is_closed")
    private Boolean isClosed = false;

    @Column(name = "best_time_to_visit")
    private String bestTimeToVisit;

    @Column(name = "price_range_top")
    private Integer priceRangeTop;

    @Column(name = "price_range_bottom")
    private Integer priceRangeBottom;

    @Column(name = "is_verified")
    private Boolean isVerified = false;

    @Column(name = "alternative_name")
    private String alternativeName;

    @Column(name = "operator")
    private String operator;

    @Column(length = 1024)
    private String url;

    public PlaceDetailEntity() {};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlaceEntity getPlace() {
        return place;
    }

    public void setPlace(PlaceEntity place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCheckInPoint() {
        return checkInPoint;
    }

    public void setCheckInPoint(Integer checkInPoint) {
        this.checkInPoint = checkInPoint;
    }

    public Float getCheckInRangeMeter() {
        return checkInRangeMeter;
    }

    public void setCheckInRangeMeter(Float checkInRangeMeter) {
        this.checkInRangeMeter = checkInRangeMeter;
    }

    public LocalTime getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(LocalTime timeOpen) {
        this.timeOpen = timeOpen;
    }

    public LocalTime getTimeClose() {
        return timeClose;
    }

    public void setTimeClose(LocalTime timeClose) {
        this.timeClose = timeClose;
    }

    public Boolean getClosed() {
        return isClosed;
    }

    public void setClosed(Boolean closed) {
        isClosed = closed;
    }

    public String getBestTimeToVisit() {
        return bestTimeToVisit;
    }

    public void setBestTimeToVisit(String bestTimeToVisit) {
        this.bestTimeToVisit = bestTimeToVisit;
    }

    public Integer getPriceRangeTop() {
        return priceRangeTop;
    }

    public void setPriceRangeTop(Integer priceRangeTop) {
        this.priceRangeTop = priceRangeTop;
    }

    public Integer getPriceRangeBottom() {
        return priceRangeBottom;
    }

    public void setPriceRangeBottom(Integer priceRangeBottom) {
        this.priceRangeBottom = priceRangeBottom;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public String getAlternativeName() {
        return alternativeName;
    }

    public void setAlternativeName(String alternativeName) {
        this.alternativeName = alternativeName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

