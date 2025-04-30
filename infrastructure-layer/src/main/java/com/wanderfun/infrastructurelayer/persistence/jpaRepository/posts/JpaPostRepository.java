package com.wanderfun.infrastructurelayer.persistence.jpaRepository.posts;

import com.wanderfun.infrastructurelayer.persistence.entity.posts.PostEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaPostRepository extends JpaBaseRepository<PostEntity, Long> {
    @Query("SELECT p FROM PostEntity p WHERE (:cursor IS NULL OR p.id < :cursor) ORDER BY p.id DESC")
    List<PostEntity> findAllPostByCursor(@Param("cursor") Long cursor, Pageable pageable);
}
