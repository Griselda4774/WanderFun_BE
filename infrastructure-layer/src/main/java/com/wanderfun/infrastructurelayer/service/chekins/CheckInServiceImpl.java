package com.wanderfun.infrastructurelayer.service.chekins;

import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.checkins.CheckInService;
import com.wanderfun.domainlayer.model.checkins.CheckIn;
import com.wanderfun.domainlayer.repository.checkins.CheckInRepository;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckInServiceImpl extends BaseServiceImpl<CheckIn, Long> implements CheckInService {
    private final CheckInRepository checkInRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public CheckInServiceImpl(CheckInRepository checkInRepository, ObjectMapper objectMapper) {
        super(checkInRepository, objectMapper, CheckIn.class);
        this.checkInRepository = checkInRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<CheckIn> findAllByUserId(Long userId) {
        return checkInRepository.findAllByUserId(userId);
    }

    @Override
    public CheckIn findLastCheckInByUserId(Long userId) {
        return checkInRepository.findLastCheckInByUserId(userId)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Last %s not found for user with ID: " + userId, CheckIn.class.getSimpleName())));
    }
}
