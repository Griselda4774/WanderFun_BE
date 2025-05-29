package com.wanderfun.infrastructurelayer.service.posts;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.posts.CommentService;
import com.wanderfun.domainlayer.model.posts.Comment;
import com.wanderfun.domainlayer.repository.posts.CommentRepository;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment, Long> implements CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ObjectMapper objectMapper) {
        super(commentRepository, objectMapper, Comment.class);
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public List<Comment> findAllByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }
}
