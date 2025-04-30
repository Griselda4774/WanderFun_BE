package com.wanderfun.infrastructurelayer.usecase;

import com.wanderfun.applicationlayer.service.posts.PostService;
import com.wanderfun.applicationlayer.usecase.PostUsecase;
import com.wanderfun.domainlayer.model.posts.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostUsecaseImpl implements PostUsecase {
    private final PostService postService;

    @Autowired
    public PostUsecaseImpl(PostService postService) {
        this.postService = postService;
    }

    @Override
    public List<Post> findAllByCursor(Long cursor, int size) {
        return postService.findAllByCursor(cursor, size);
    }
}
