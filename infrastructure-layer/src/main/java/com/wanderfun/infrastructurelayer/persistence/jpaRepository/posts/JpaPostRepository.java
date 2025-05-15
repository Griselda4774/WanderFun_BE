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
        p.id, p.user, p.content, p.createAt, p.updateAt, p.place, p.isTripShare, p.trip, p.image,
        (SELECT CAST(COUNT(l) AS long) FROM LikeEntity l WHERE l.targetId = p.id AND l.targetType = 'POST'),
        (SELECT CAST(COUNT(c) AS long) FROM CommentEntity c WHERE c.post.id = p.id)
    )
    FROM PostEntity p
    WHERE (:cursor IS NULL OR p.id < :cursor)
    ORDER BY p.id DESC
""")
    List<PostEntity> findAllPostByCursor(@Param("cursor") Long cursor, Pageable pageable);
}
