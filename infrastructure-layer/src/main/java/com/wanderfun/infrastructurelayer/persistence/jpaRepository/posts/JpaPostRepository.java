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

    @Query("SELECT p FROM PostEntity p WHERE p.user.id = :user_id ORDER BY p.id DESC")
    List<PostEntity> findAllByUserId(@Param("user_id") Long userId);

    @Query("SELECT CAST(COUNT(c) AS long) FROM CommentEntity c WHERE c.post.id = :post_id")
    Long countCommentById(@Param("post_id")Long postId);

    @Query("SELECT CAST(COUNT(l) AS long) FROM LikeEntity l WHERE l.targetId = :post_id AND l.targetType = 'POST'")
    Long countLikeById(@Param("post_id")Long postId);
}
