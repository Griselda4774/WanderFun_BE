package com.wanderfun.infrastructurelayer.repository.posts;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.posts.Comment;
import com.wanderfun.domainlayer.repository.posts.CommentRepository;
import com.wanderfun.infrastructurelayer.persistence.entity.posts.CommentEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.posts.PostEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.posts.JpaCommentRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryImpl extends BaseRepositoryImpl<Comment, CommentEntity, Long> implements CommentRepository {
    private final JpaCommentRepository jpaCommentRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public CommentRepositoryImpl(JpaCommentRepository jpaCommentRepository, ObjectMapper objectMapper) {
        super(jpaCommentRepository, objectMapper, Comment.class, CommentEntity.class);
        this.jpaCommentRepository = jpaCommentRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Comment save(Comment comment) {
        CommentEntity commentEntity = objectMapper.map(comment, CommentEntity.class);
        if (comment.getPostId() != null && comment.getPostId() > 0) {
            commentEntity.setPost(new PostEntity());
            commentEntity.getPost().setId(comment.getPostId());
        } else {
            throw new IllegalArgumentException("Post ID must be provided for the comment.");
        }

        if (comment.getParentId() != null && comment.getParentId() > 0) {
            commentEntity.setParent(new CommentEntity());
            commentEntity.getParent().setId(comment.getParentId());
        } else {
            commentEntity.setParent(null);
        }

        CommentEntity savedCommentEntity = jpaCommentRepository.save(commentEntity);

        return objectMapper.map(savedCommentEntity, Comment.class);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return jpaCommentRepository.findById(id)
                .map(commentEntity -> {
                    Comment comment = objectMapper.map(commentEntity, Comment.class);
                    comment.setLikeCount(jpaCommentRepository.countLikeById(comment.getId()));
                    return comment;
                });
    }

    @Override
    public List<Comment> findAllByPostId(Long postId) {
        List<Comment> commentList = objectMapper.mapList(jpaCommentRepository
                .findAllByPostId(postId), Comment.class);
        commentList.forEach(comment -> {
            comment.setLikeCount(jpaCommentRepository.countLikeById(comment.getId()));
        });
        return commentList;
    }
}
