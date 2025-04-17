//package com.wanderfun.infrastructurelayer.persistence.jpaRepository.trips;
//
//import com.wanderfun.infrastructurelayer.persistence.entity.trips.TripEntity;
//import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface JpaTripRepository extends JpaBaseRepository<TripEntity, Long> {
//    Optional<TripEntity> findByName(String name);
//    List<TripEntity> findAllByUser_Id(Long userId);
//    void deleteAllByUser_Id(Long userId);
//}
