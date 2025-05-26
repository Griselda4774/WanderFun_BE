package com.wanderfun.infrastructurelayer.service.albums;

import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.repository.albums.AlbumRepository;
import com.wanderfun.applicationlayer.service.albums.AlbumService;
import com.wanderfun.domainlayer.model.albums.Album;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl extends BaseServiceImpl<Album, Long> implements AlbumService {
    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository, ObjectMapper objectMapper) {
        super(albumRepository, objectMapper, Album.class);
        this.albumRepository = albumRepository;
    }

    @Override
    @Transactional
    public Album findByName(String name) {
        return albumRepository.findByName(name)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", Album.class.getSimpleName())));
    }

    @Override
    @Transactional
    public List<Album> findAllByUserId(Long userId) {
        return albumRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public void deleteAllByUserId(Long userId) {
        albumRepository.deleteAllByUserId(userId);
    }
}
