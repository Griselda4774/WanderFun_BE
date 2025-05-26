package com.wanderfun.infrastructurelayer.repository.albums;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.albums.AlbumImage;
import com.wanderfun.domainlayer.repository.albums.AlbumImageRepository;
import com.wanderfun.infrastructurelayer.persistence.entity.albums.AlbumImageEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.albums.JpaAlbumImageRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AlbumImageRepositoryImpl extends BaseRepositoryImpl<AlbumImage, AlbumImageEntity, Long> implements AlbumImageRepository {

    @Autowired
    public AlbumImageRepositoryImpl(JpaAlbumImageRepository jpaAlbumImageRepository, ObjectMapper objectMapper) {
        super(jpaAlbumImageRepository, objectMapper, AlbumImage.class, AlbumImageEntity.class);
    }
}
