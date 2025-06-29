package com.wanderfun.infrastructurelayer.repository.chekins;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.checkins.CheckIn;
import com.wanderfun.domainlayer.repository.checkins.CheckInRepository;
import com.wanderfun.infrastructurelayer.persistence.entity.checkins.CheckInEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.checkins.JpaCheckInRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CheckInRepositoryImpl extends BaseRepositoryImpl<CheckIn, CheckInEntity, Long> implements CheckInRepository {
    private final JpaCheckInRepository jpaCheckInRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public CheckInRepositoryImpl(JpaCheckInRepository jpaCheckInRepository, ObjectMapper objectMapper) {
        super(jpaCheckInRepository, objectMapper, CheckIn.class, CheckInEntity.class);
        this.jpaCheckInRepository = jpaCheckInRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<CheckIn> findAllByUserId(Long userId) {
        return objectMapper.mapList(jpaCheckInRepository.findAllByUserId(userId), CheckIn.class);
    }

    @Override
    public Optional<CheckIn> findLastCheckInByUserId(Long userId) {
        return jpaCheckInRepository.findLastCheckInByUserId(userId)
                .map(checkInEntity -> objectMapper.map(checkInEntity, CheckIn.class));
    }

    @Override
    public Long countTotalCheckInsToday() {
        return jpaCheckInRepository.countTotalCheckInsToday();
    }
}
