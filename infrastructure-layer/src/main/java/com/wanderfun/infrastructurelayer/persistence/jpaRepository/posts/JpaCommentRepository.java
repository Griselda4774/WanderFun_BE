package com.wanderfun.infrastructurelayer.persistence.jpaRepository.posts;

import com.wanderfun.infrastructurelayer.persistence.entity.posts.CommentEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaCommentRepository extends JpaBaseRepository<CommentEntity, Long> {
    @Query("SELECT c FROM CommentEntity c WHERE c.post.id = :post_id ORDER BY c.createAt DESC")
    List<CommentEntity> findAllByPostId(@Param("post_id")Long postId);

    @Query("SELECT CAST(COUNT(l) AS long) FROM LikeEntity l WHERE l.targetId = :comment_id AND l.targetType = 'COMMENT'")
    Long countLikeById(@Param("comment_id")Long commentId);
}
