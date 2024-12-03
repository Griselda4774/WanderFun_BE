package com.project2.wanderfun.infrastructure.service;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.SectionRepository;
import com.project2.wanderfun.application.service.SectionService;
import com.project2.wanderfun.domain.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImpl extends BaseServiceImpl<Section> implements SectionService {

    @Autowired
    public SectionServiceImpl(SectionRepository sectionRepository, ObjectMapper objectMapper) {
        super(sectionRepository, objectMapper, Section.class);
    }
}
