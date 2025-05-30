package com.wanderfun.infrastructurelayer.usecase;

import com.wanderfun.applicationlayer.dto.albums.AlbumCreateDto;
import com.wanderfun.applicationlayer.dto.albums.AlbumDto;
import com.wanderfun.applicationlayer.exception.ObjectAlreadyExistException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.albums.AlbumService;
import com.wanderfun.applicationlayer.service.place.PlaceService;
import com.wanderfun.applicationlayer.usecase.AlbumUsecase;
import com.wanderfun.applicationlayer.util.JwtUtil;
import com.wanderfun.domainlayer.model.albums.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlbumUsecaseImp implements AlbumUsecase {
    private final AlbumService albumService;
    private final PlaceService placeService;
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;

    @Autowired
    public AlbumUsecaseImp(AlbumService albumService, PlaceService placeService, ObjectMapper objectMapper, JwtUtil jwtUtil) {
        this.albumService = albumService;
        this.placeService = placeService;
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public List<AlbumDto> findAllAlbums(String accessToken) {
        return objectMapper.mapList(albumService.findAllByUserId(jwtUtil.getIdFromToken(accessToken)), AlbumDto.class);
    }

    @Override
    public AlbumDto findAlbumById(Long id) {
        return objectMapper.map(albumService.findById(id), AlbumDto.class);
    }

    @Override
    public boolean createAlbum(AlbumCreateDto albumCreateDto, String accessToken) throws ObjectAlreadyExistException {
        Album album = objectMapper.map(albumCreateDto, Album.class);
        Album existingAlbum = null;
        try {
            existingAlbum = albumService.findByName(album.getName());
        } catch (Exception e) {}

        if(existingAlbum != null) {
            throw new ObjectAlreadyExistException("This name is already used!");
        }
        if (albumCreateDto.getPlaceId() == null) {
            throw new ObjectAlreadyExistException("Place ID cannot be null!");
        }
        album.setPlace(placeService.findById(albumCreateDto.getPlaceId()));

        album.setUserId(jwtUtil.getIdFromToken(accessToken));
        album.setCreatedAt(LocalDateTime.now());
        album.setUpdatedAt(LocalDateTime.now());
        albumService.create(album);
        return true;
    }

    @Override
    public boolean updateAlbumById(Long id, AlbumCreateDto albumCreateDto) throws ObjectAlreadyExistException{
        Album album = objectMapper.map(albumCreateDto, Album.class);
        var testCurrentAlbum = albumService.findById(id);
        Album currentAlbum = albumService.findById(id);
        if (!album.getName().equals(currentAlbum.getName())) {
            Album existingAlbum;
            try {
                existingAlbum = albumService.findByName(album.getName());
            } catch (Exception e) {
                existingAlbum = null;
            }
            if (existingAlbum != null) {
                throw new ObjectAlreadyExistException("This name is already used!");
            }
        }

        if (albumCreateDto.getPlaceId() != null) {
            album.setPlace(placeService.findById(albumCreateDto.getPlaceId()));
        }

        album.setUpdatedAt(LocalDateTime.now());
        albumService.updateById(id, album);
        return true;
    }

    @Override
    public boolean deleteAlbumById(Long id) {
        albumService.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAllAlbums(String accessToken) {
        albumService.deleteAllByUserId(jwtUtil.getIdFromToken(accessToken));
        return true;
    }
}
