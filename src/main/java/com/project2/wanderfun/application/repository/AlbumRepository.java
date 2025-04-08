package com.project2.wanderfun.application.repository;

import com.project2.wanderfun.domain.model.albums.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends BaseRepository<Album, Long> {
    Optional<Album> findByName(String name);
    List<Album> findAllByUser_Id(Long userId);
    void deleteAllByUser_Id(Long userId);
}
