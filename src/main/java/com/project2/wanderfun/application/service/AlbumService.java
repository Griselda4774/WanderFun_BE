package com.project2.wanderfun.application.service;

import com.project2.wanderfun.domain.model.Album;

import java.util.List;

public interface AlbumService extends BaseService<Album> {
    Album findByName(String name);
    List<Album> findAllByUserId(Long userId);
    void deleteAllByUserId(Long userId);
}
