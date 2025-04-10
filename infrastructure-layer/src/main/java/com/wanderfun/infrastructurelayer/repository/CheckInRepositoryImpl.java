package com.wanderfun.infrastructurelayer.repository;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.repository.CheckInRepository;
import com.wanderfun.domainlayer.model.checkins.CheckIn;
import com.wanderfun.infrastructurelayer.persistence.entity.CheckInEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.UserEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaCheckInRepository;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CheckInRepositoryImpl extends BaseRepositoryImpl<CheckIn, CheckInEntity, Long> implements CheckInRepository {
    private final JpaCheckInRepository jpaCheckInRepository;
    private final JpaUserRepository jpaUserRepository;

    @Autowired
    public CheckInRepositoryImpl(JpaCheckInRepository jpaCheckInRepository, ObjectMapper objectMapper, JpaUserRepository jpaUserRepository) {
        super(jpaCheckInRepository, objectMapper, CheckIn.class, CheckInEntity.class);
        this.jpaCheckInRepository = jpaCheckInRepository;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public CheckIn save(CheckIn CheckIn) {
        CheckInEntity checkInEntity = objectMapper.map(CheckIn, CheckInEntity.class);

        UserEntity userEntity = jpaUserRepository.findById(CheckIn.getUserId()).get();
        checkInEntity.setUser(userEntity);

        CheckInEntity savedCheckInEntity = jpaCheckInRepository.save(checkInEntity);
        return objectMapper.map(savedCheckInEntity, CheckIn.class);
    }

    @Override
    public List<CheckIn> findAllByUser_Id(Long userId) {
        List<CheckIn> checkIns = objectMapper.mapList(jpaCheckInRepository.findAllByUser_Id(userId), CheckIn.class);
        return checkIns;
    }

    @Override
    public Optional<CheckIn> findByPlaceIdAndUser_Id(Long placeId, Long userId) {
        Optional<CheckIn> checkIn = jpaCheckInRepository.findByPlaceIdAndUser_Id(placeId, userId)
                .map(entity -> objectMapper.map(entity, CheckIn.class));
        return checkIn;
    }
}
