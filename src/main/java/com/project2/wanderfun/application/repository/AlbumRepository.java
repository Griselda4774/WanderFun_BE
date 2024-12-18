package com.project2.wanderfun.application.repository;

import com.project2.wanderfun.domain.model.Album;

import java.util.Optional;

public interface AlbumRepository extends BaseRepository<Album, Long> {
    Optional<Album> findByName(String name);
}
