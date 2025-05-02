package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.applicationlayer.dto.posts.PostCreateDto;
import com.wanderfun.applicationlayer.dto.posts.PostDto;
import java.util.List;

public interface PostUsecase {
    List<PostDto> findAllByCursor(Long cursor, int size);
    boolean createPost(PostCreateDto postCreateDto, String accessToken);
    boolean updatePost(Long postId, PostCreateDto postDto, String accessToken);
    boolean deletePost(Long postId, String accessToken);
}
