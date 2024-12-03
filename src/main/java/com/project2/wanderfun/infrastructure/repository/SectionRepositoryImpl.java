package com.project2.wanderfun.infrastructure.repository;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.SectionRepository;
import com.project2.wanderfun.domain.model.RefreshToken;
import com.project2.wanderfun.domain.model.Section;
import com.project2.wanderfun.infrastructure.persistence.entity.RefreshTokenEntity;
import com.project2.wanderfun.infrastructure.persistence.entity.SectionEntity;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.JpaRefreshTokenRepository;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.JpaSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SectionRepositoryImpl extends BaseRepositoryImpl<Section, SectionEntity, Long> implements SectionRepository {
    private final JpaSectionRepository jpaSectionRepository;

    @Autowired
    public SectionRepositoryImpl(JpaSectionRepository jpaSectionRepository, ObjectMapper objectMapper) {
        super(jpaSectionRepository, objectMapper, Section.class, SectionEntity.class);
        this.jpaSectionRepository = jpaSectionRepository;
    }
}
