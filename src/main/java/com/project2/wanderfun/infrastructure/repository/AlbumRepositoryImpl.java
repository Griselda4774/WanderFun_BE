package com.project2.wanderfun.infrastructure.repository;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.AlbumRepository;
import com.project2.wanderfun.domain.model.Album;
import com.project2.wanderfun.infrastructure.persistence.entity.*;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.JpaAlbumRepository;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlbumRepositoryImpl extends BaseRepositoryImpl<Album, AlbumEntity, Long> implements AlbumRepository {
    private final JpaAlbumRepository jpaAlbumRepository;
    private final JpaUserRepository jpaUserRepository;

    @Autowired
    public AlbumRepositoryImpl(JpaAlbumRepository jpaAlbumRepository, ObjectMapper objectMapper, JpaUserRepository jpaUserRepository) {
        super(jpaAlbumRepository, objectMapper, Album.class, AlbumEntity.class);
        this.jpaAlbumRepository = jpaAlbumRepository;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Album save(Album album) {
        AlbumEntity albumEntity = objectMapper.map(album, AlbumEntity.class);

        if (album.getAlbumImages() != null || album.getAlbumImages().size() > 0) {
            List<AlbumImageEntity> albumImageEntities = objectMapper.mapList(album.getAlbumImages(), AlbumImageEntity.class);
            albumImageEntities.forEach(albumImageEntity -> albumImageEntity.setAlbum(albumEntity));
            albumEntity.setAlbumImages(albumImageEntities);
        }

        UserEntity userEntity = jpaUserRepository.findById(album.getUserId()).get();
        albumEntity.setUser(userEntity);

        AlbumEntity savedAlbumEntity = jpaAlbumRepository.save(albumEntity);

        return objectMapper.map(savedAlbumEntity, Album.class);
    }

    @Override
    public Optional<Album> findByName(String name) {
        Optional<Album> album = jpaAlbumRepository.findByName(name)
                .map(entity -> objectMapper.map(entity, Album.class));
        return album;
    }
}
