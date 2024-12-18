package com.project2.wanderfun.application.service;

import com.project2.wanderfun.domain.model.Album;

public interface AlbumService extends BaseService<Album> {
    public Album findByName(String name);
}
