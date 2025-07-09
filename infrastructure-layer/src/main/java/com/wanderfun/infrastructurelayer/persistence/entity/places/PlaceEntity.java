package com.wanderfun.infrastructurelayer.persistence.entity.places;

import com.wanderfun.infrastructurelayer.persistence.entity.addresses.AddressEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.images.ImageEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "places")
public class PlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double latitude;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", nullable = false)
    private AddressEntity address;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private PlaceCategoryEntity category;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cover_image_id")
    private ImageEntity coverImage;

    @Column(name = "check_in_point")
    private Integer checkInPoint;

    @Column(name = "check_in_range_meter")
    private Float checkInRangeMeter;

    @Column(name = "verified")
    private Boolean verified;

    @Column(name = "pending")
    private Boolean pending;

    @Column(name = "rating", nullable = false)
    private float rating;

    @Column(name = "total_rating", nullable = false)
    private int totalRating;

    @Column(name = "feedback_count", nullable = false)
    private int feedbackCount;

    @Column(name = "check_in_count", nullable = false)
    private int checkInCount;

    public PlaceEntity() {};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlaceCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(PlaceCategoryEntity category) {
        this.category = category;
    }

    public ImageEntity getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(ImageEntity coverImage) {
        this.coverImage = coverImage;
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

    public Boolean isVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Boolean isPending() {
        return pending;
    }

    public void setPending(Boolean pending) {
        this.pending = pending;
    }

    public int getFeedbackCount() {
        return feedbackCount;
    }

    public void setFeedbackCount(int feedbackCount) {
        this.feedbackCount = feedbackCount;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public int getCheckInCount() {
        return checkInCount;
    }

    public void setCheckInCount(int checkInCount) {
        this.checkInCount = checkInCount;
    }
}

