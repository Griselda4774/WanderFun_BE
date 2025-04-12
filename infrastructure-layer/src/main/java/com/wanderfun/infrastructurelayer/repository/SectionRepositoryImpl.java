//package com.wanderfun.infrastructurelayer.repository;
//
//import com.wanderfun.applicationlayer.mapper.ObjectMapper;
//import com.wanderfun.domainlayer.repository.SectionRepository;
//import com.wanderfun.domainlayer.model.places.Section;
//import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaSectionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class SectionRepositoryImpl extends BaseRepositoryImpl<Section, SectionEntity, Long> implements SectionRepository {
//    private final JpaSectionRepository jpaSectionRepository;
//
//    @Autowired
//    public SectionRepositoryImpl(JpaSectionRepository jpaSectionRepository, ObjectMapper objectMapper) {
//        super(jpaSectionRepository, objectMapper, Section.class, SectionEntity.class);
//        this.jpaSectionRepository = jpaSectionRepository;
//    }
//
//    @Override
//    public List<Section> findAllByPlaceId(Long placeId) {
//        return jpaSectionRepository.findAllByPlaceId(placeId)
//                .stream()
//                .map(entity -> objectMapper.map(entity, Section.class))
//                .toList();
//    }
//}
