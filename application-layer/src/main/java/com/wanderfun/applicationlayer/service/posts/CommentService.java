package com.wanderfun.applicationlayer.service.posts;

import com.wanderfun.applicationlayer.service.BaseService;
import com.wanderfun.domainlayer.model.posts.Comment;

import java.util.List;

public interface CommentService extends BaseService<Comment, Long> {
    List<Comment> findAllByPostId(Long postId);
}
