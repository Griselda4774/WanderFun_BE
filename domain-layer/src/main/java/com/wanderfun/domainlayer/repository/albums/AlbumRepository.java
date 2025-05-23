package com.wanderfun.domainlayer.repository.albums;

import com.wanderfun.domainlayer.model.albums.Album;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends BaseRepository<Album, Long> {
    Optional<Album> findByName(String name);
    List<Album> findAllByUser_Id(Long userId);
    void deleteAllByUser_Id(Long userId);
}
