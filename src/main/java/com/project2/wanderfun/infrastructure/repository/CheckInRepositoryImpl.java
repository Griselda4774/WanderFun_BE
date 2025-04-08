package com.project2.wanderfun.infrastructure.repository;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.CheckInRepository;
import com.project2.wanderfun.domain.model.users.CheckIn;
import com.project2.wanderfun.infrastructure.persistence.entity.CheckInEntity;
import com.project2.wanderfun.infrastructure.persistence.entity.UserEntity;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.JpaCheckInRepository;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.JpaUserRepository;
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
