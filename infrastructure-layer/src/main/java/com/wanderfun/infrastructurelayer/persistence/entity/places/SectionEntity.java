package com.wanderfun.infrastructurelayer.persistence.entity.places;

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

    @Column(name = "image_id")
    private Long imageId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "place_id", nullable = false)
    private PlaceDetailEntity place;

    public SectionEntity() {}
}

