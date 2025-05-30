package com.wanderfun.infrastructurelayer.service.albums;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.albums.AlbumImageService;
import com.wanderfun.domainlayer.model.albums.AlbumImage;
import com.wanderfun.domainlayer.repository.albums.AlbumImageRepository;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumImageServiceImpl extends BaseServiceImpl<AlbumImage, Long> implements AlbumImageService {

    @Autowired
    public AlbumImageServiceImpl(AlbumImageRepository albumImageRepository, ObjectMapper objectMapper) {
        super(albumImageRepository, objectMapper, AlbumImage.class);
    }
}
