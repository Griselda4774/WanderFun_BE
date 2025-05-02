package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.applicationlayer.dto.posts.PostCreateDto;
import com.wanderfun.applicationlayer.dto.posts.PostDto;
import java.util.List;

public interface PostUsecase {
    List<PostDto> findAllByCursor(Long cursor, int size);
    boolean createPost(PostCreateDto postCreateDto);
    boolean updatePost(Long postId, PostDto postDto);
    boolean deletePost(Long postId);
}
