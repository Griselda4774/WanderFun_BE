package com.project2.wanderfun.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "album")
public class AlbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    private String name;
    private String placeId;
    private String description;

    // Relationships
    @OneToMany(mappedBy = "album")
    private List<AlbumImageEntity> albumImages;
}
