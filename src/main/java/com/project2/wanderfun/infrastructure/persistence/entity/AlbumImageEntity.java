package com.project2.wanderfun.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "album_image")
public class AlbumImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "albumId")
    private AlbumEntity album;

    private String imageUrl;
}