package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.domainlayer.model.posts.Post;

import java.util.List;

public interface PostUsecase {
    List<Post> findAllByCursor(Long cursor, int size);
}
