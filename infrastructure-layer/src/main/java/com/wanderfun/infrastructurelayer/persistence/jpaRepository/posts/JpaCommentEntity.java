package com.wanderfun.infrastructurelayer.persistence.jpaRepository.posts;

import com.wanderfun.infrastructurelayer.persistence.entity.posts.CommentEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.posts.PostEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaCommentEntity extends JpaBaseRepository<CommentEntity, Long> {
    @Query("SELECT c FROM CommentEntity c WHERE c.post.id = :post_id")
    List<CommentEntity> findAllByPost_Id(@Param("post_id")Long postId);
}
