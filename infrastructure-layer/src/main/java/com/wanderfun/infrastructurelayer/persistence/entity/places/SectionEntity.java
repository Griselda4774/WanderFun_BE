package com.wanderfun.infrastructurelayer.persistence.entity.places;

import com.wanderfun.infrastructurelayer.persistence.entity.images.ImageEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "sections")
public class SectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    @ManyToOne(optional = false)
    @JoinColumn(name = "place_detail_id", nullable = false)
    private PlaceDetailEntity placeDetail;

    public SectionEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    public PlaceDetailEntity getPlaceDetail() {
        return placeDetail;
    }

    public void setPlaceDetail(PlaceDetailEntity placeDetail) {
        this.placeDetail = placeDetail;
    }
}

