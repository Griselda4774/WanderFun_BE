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
    @Query("""
            SELECT new com.wanderfun.infrastructurelayer.persistence.entity.posts.PostEntity(
                p.id, p.user, p.content, p.createAt, p.updateAt, p.place, p.isTripShare, p.trip, p.image, COUNT(l.id))
            FROM PostEntity p
            LEFT JOIN FETCH p.user
            LEFT JOIN FETCH p.place
            LEFT JOIN FETCH p.trip
            LEFT JOIN FETCH p.image
            LEFT JOIN LikeEntity l ON l.targetId = p.id AND l.targetType = 'POST'
            WHERE (:cursor IS NULL OR p.id < :cursor)
            GROUP BY p.id, p.user, p.content, p.createAt, p.updateAt, p.place, p.isTripShare, p.trip, p.image
            ORDER BY p.id DESC
    """)
    List<PostEntity> findAllPostByCursor(@Param("cursor") Long cursor, Pageable pageable);
}
