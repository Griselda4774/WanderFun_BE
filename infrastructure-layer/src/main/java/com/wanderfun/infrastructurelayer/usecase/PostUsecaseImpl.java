package com.wanderfun.infrastructurelayer.usecase;

import com.wanderfun.applicationlayer.dto.posts.PostCreateDto;
import com.wanderfun.applicationlayer.dto.posts.PostDto;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.posts.PostService;
import com.wanderfun.applicationlayer.usecase.PostUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostUsecaseImpl implements PostUsecase {
    private final PostService postService;
    private final ObjectMapper objectMapper;

    @Autowired
    public PostUsecaseImpl(PostService postService, ObjectMapper objectMapper) {
        this.postService = postService;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<PostDto> findAllByCursor(Long cursor, int size) {
        return objectMapper.mapList(postService.findAllByCursor(cursor, size), PostDto.class);
    }

    @Override
    public boolean createPost(PostCreateDto postCreateDto) {

        return true;
    }

    @Override
    public boolean updatePost(Long postId, PostDto postDto) {

    }

    @Override
    public boolean deletePost(Long postId) {

    }
}
