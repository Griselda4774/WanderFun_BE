package com.wanderfun.infrastructurelayer.persistence.entity.addresses;

import com.wanderfun.infrastructurelayer.persistence.entity.images.ImageEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "province_details")
public class ProvinceDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "province_code", nullable = false)
    private ProvinceEntity province;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double latitude;

    @Column(name = "area")
    private Double area;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "best_time_to_visit")
    private String bestTimeToVisit;

    @Column(columnDefinition = "TEXT")
    private String festivals;

    @Column(name = "local_transport", columnDefinition = "TEXT")
    private String localTransport;

    @Column(name = "local_food", columnDefinition = "TEXT")
    private String localFood;

    @Column(name = "how_to_get_there", columnDefinition = "TEXT")
    private String howToGetThere;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cover_image_id")
    private ImageEntity coverImage;

    public ProvinceDetailEntity() {};

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProvinceEntity getProvince() {
        return province;
    }

    public void setProvince(ProvinceEntity province) {
        this.province = province;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBestTimeToVisit() {
        return bestTimeToVisit;
    }

    public void setBestTimeToVisit(String bestTimeToVisit) {
        this.bestTimeToVisit = bestTimeToVisit;
    }

    public String getFestivals() {
        return festivals;
    }

    public void setFestivals(String festivals) {
        this.festivals = festivals;
    }

    public String getLocalTransport() {
        return localTransport;
    }

    public void setLocalTransport(String localTransport) {
        this.localTransport = localTransport;
    }

    public String getLocalFood() {
        return localFood;
    }

    public void setLocalFood(String localFood) {
        this.localFood = localFood;
    }

    public String getHowToGetThere() {
        return howToGetThere;
    }

    public void setHowToGetThere(String howToGetThere) {
        this.howToGetThere = howToGetThere;
    }

    public ImageEntity getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(ImageEntity coverImage) {
        this.coverImage = coverImage;
    }
}

