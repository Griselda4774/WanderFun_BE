package com.wanderfun.applicationlayer.service;

import com.wanderfun.domainlayer.model.albums.Album;

import java.util.List;

public interface AlbumService extends BaseService<Album, Long> {
    Album findByName(String name);
    List<Album> findAllByUserId(Long userId);
    void deleteAllByUserId(Long userId);
}
