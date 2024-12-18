package com.project2.wanderfun.infrastructure.service;

import com.project2.wanderfun.application.exception.ObjectNotFoundException;
import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.AlbumRepository;
import com.project2.wanderfun.application.service.AlbumService;
import com.project2.wanderfun.domain.model.Album;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl extends BaseServiceImpl<Album> implements AlbumService {
    private final AlbumRepository albumRepository;

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
}
