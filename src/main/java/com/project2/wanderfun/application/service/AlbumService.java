package com.project2.wanderfun.application.service;

import com.project2.wanderfun.domain.model.Album;

import java.util.List;

public interface AlbumService extends BaseService<Album> {
    public Album findByName(String name);
    public List<Album> findAllByUserId(Long userId);
    public void deleteAllByUserId(Long userId);
}
