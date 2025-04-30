package com.wanderfun.domainlayer.repository.posts;

import com.wanderfun.domainlayer.model.posts.Post;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.List;

public interface PostRepository extends BaseRepository<Post, Long> {
    List<Post> findAllByCursor(Long cursor, int size);
}
