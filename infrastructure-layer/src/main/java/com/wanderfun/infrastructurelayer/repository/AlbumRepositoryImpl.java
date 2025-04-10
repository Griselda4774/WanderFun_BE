package com.wanderfun.infrastructurelayer.repository;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.repository.AlbumRepository;
import com.wanderfun.domainlayer.model.albums.Album;
import com.wanderfun.infrastructurelayer.persistence.entity.*;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaAlbumRepository;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaUserRepository;
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

    @Override
    public List<Album> findAllByUser_Id(Long userId) {
        List<Album> albums = objectMapper.mapList(jpaAlbumRepository.findAllByUser_Id(userId), Album.class);
        return albums;
    }

    @Override
    public void deleteAllByUser_Id(Long userId) {
        jpaAlbumRepository.deleteAllByUser_Id(userId);
    }
}
