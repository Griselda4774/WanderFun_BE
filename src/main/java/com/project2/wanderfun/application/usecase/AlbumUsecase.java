package com.project2.wanderfun.application.usecase;

import com.project2.wanderfun.application.dto.album.AlbumCreateDto;
import com.project2.wanderfun.application.dto.album.AlbumDto;
import com.project2.wanderfun.application.exception.ObjectAlreadyExistException;
import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.service.AlbumService;
import com.project2.wanderfun.application.util.JwtUtil;
import com.project2.wanderfun.domain.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AlbumUsecase {
    private final AlbumService albumService;
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;

    @Autowired
    public AlbumUsecase(AlbumService albumService, ObjectMapper objectMapper, JwtUtil jwtUtil) {
        this.albumService = albumService;
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
    }

    public List<AlbumDto> findAllAlbums(String accessToken) {
        return objectMapper.mapList(albumService.findAllByUserId(jwtUtil.getIdFromToken(accessToken)), AlbumDto.class);
    }

    public AlbumDto findAlbumById(Long id) {
        return objectMapper.map(albumService.findById(id), AlbumDto.class);
    }

    public boolean createAlbum(AlbumCreateDto albumCreateDto, String accessToken) throws ObjectAlreadyExistException {
        Album album = objectMapper.map(albumCreateDto, Album.class);
        Album existingAlbum = null;
        try {
            existingAlbum = albumService.findByName(album.getName());
        } catch (Exception e) {}

        if(existingAlbum != null) {
            throw new ObjectAlreadyExistException("This name is already used!");
        }

        album.setUserId(jwtUtil.getIdFromToken(accessToken));
        album.setLastModified(new Date());
        albumService.create(album);
        return true;
    }

    public boolean updateAlbumById(Long id, AlbumCreateDto albumCreateDto) throws ObjectAlreadyExistException{
        Album album = objectMapper.map(albumCreateDto, Album.class);

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

        album.setLastModified(new Date());
        albumService.updateById(id, album);
        return true;
    }

    public boolean deleteAlbumById(Long id) {
        albumService.deleteById(id);
        return true;
    }

    public boolean deleteAllAlbums(String accessToken) {
        albumService.deleteAllByUserId(jwtUtil.getIdFromToken(accessToken));
        return true;
    }
}
