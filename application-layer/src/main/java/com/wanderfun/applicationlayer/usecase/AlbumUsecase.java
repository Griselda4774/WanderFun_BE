package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.applicationlayer.dto.albums.AlbumCreateDto;
import com.wanderfun.applicationlayer.dto.albums.AlbumDto;

import java.util.List;

public interface AlbumUsecase {
    List<AlbumDto> findAllAlbums(String accessToken);
    AlbumDto findAlbumById(Long id);
    boolean createAlbum(AlbumCreateDto albumCreateDto, String accessToken);
    boolean updateAlbumById(Long id, AlbumCreateDto albumCreateDto);
    boolean deleteAlbumById(Long id);
    boolean deleteAllAlbums(String accessToken);
}
