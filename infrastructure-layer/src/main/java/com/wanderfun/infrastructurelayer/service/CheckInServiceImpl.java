//package com.wanderfun.infrastructurelayer.service;
//
//import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
//import com.wanderfun.applicationlayer.mapper.ObjectMapper;
//import com.wanderfun.domainlayer.repository.CheckInRepository;
//import com.wanderfun.applicationlayer.service.CheckInService;
//import com.wanderfun.domainlayer.model.checkins.CheckIn;
//import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CheckInServiceImpl extends BaseServiceImpl<CheckIn> implements CheckInService {
//    private final CheckInRepository checkInRepository;
//
//    public CheckInServiceImpl(CheckInRepository checkInRepository, ObjectMapper objectMapper) {
//        super(checkInRepository, objectMapper, CheckIn.class);
//        this.checkInRepository = checkInRepository;
//    }
//
//    @Override
//    @Transactional
//    public List<CheckIn> findAllByUserId(Long userId) {
//        List<CheckIn> checkIns = checkInRepository.findAllByUser_Id(userId);
//        return checkIns;
//    }
//
//    @Override
//    @Transactional
//    public CheckIn findByPlaceIdAndUserId(Long placeId, Long userId) {
//        return checkInRepository.findByPlaceIdAndUser_Id(placeId, userId)
//                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", CheckIn.class.getSimpleName())));
//    }
//}
