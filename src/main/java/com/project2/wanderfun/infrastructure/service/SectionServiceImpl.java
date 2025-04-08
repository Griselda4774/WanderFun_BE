package com.project2.wanderfun.infrastructure.service;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.SectionRepository;
import com.project2.wanderfun.application.service.SectionService;
import com.project2.wanderfun.domain.model.places.Section;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImpl extends BaseServiceImpl<Section> implements SectionService {

    public SectionServiceImpl(SectionRepository sectionRepository, ObjectMapper objectMapper) {
        super(sectionRepository, objectMapper, Section.class);
    }
}
