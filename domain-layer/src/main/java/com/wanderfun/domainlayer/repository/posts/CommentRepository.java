package com.wanderfun.domainlayer.repository.posts;

import com.wanderfun.domainlayer.model.posts.Comment;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.List;

public interface CommentRepository extends BaseRepository<Comment, Long> {
    List<Comment> findAllByPostId(Long postId);
}
