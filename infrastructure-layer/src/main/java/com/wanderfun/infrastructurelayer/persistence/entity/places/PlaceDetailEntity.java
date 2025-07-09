package com.wanderfun.infrastructurelayer.persistence.entity.places;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "place_details")
public class PlaceDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "place_id", nullable = false)
    private PlaceEntity place;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "time_open")
    private LocalTime timeOpen;

    @Column(name = "time_close")
    private LocalTime timeClose;

    @Column(name = "is_closed")
    private Boolean isClosed = false;

    @Column(name = "is_open_all_day")
    private Boolean isOpenAllDay = false;

    @Column(name = "best_time_to_visit")
    private String bestTimeToVisit;

    @Column(name = "price_range_top")
    private Integer priceRangeTop;

    @Column(name = "price_range_bottom")
    private Integer priceRangeBottom;

    @Column(name = "alternative_name")
    private String alternativeName;

    @Column(name = "operator")
    private String operator;

    @Column(length = 1024)
    private String url;

    @OneToMany(mappedBy = "placeDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SectionEntity> sectionList = new ArrayList<>();

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

    public Boolean getOpenAllDay() {
        return isOpenAllDay;
    }

    public void setOpenAllDay(Boolean openAllDay) {
        isOpenAllDay = openAllDay;
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

    public List<SectionEntity> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<SectionEntity> sectionList) {
        this.sectionList = sectionList;
    }
}

