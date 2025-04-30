package com.wanderfun.applicationlayer.service.posts;

import com.wanderfun.applicationlayer.service.BaseService;
import com.wanderfun.domainlayer.model.posts.Post;

import java.util.List;

public interface PostService extends BaseService<Post, Long> {
    List<Post> findAllByCursor(Long cursor, int size);
}
