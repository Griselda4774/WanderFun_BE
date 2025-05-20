package com.wanderfun.infrastructurelayer.service.posts;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.posts.PostService;
import com.wanderfun.domainlayer.model.posts.Post;
import com.wanderfun.domainlayer.repository.posts.PostRepository;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl extends BaseServiceImpl<Post, Long> implements PostService {
    private final PostRepository postRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ObjectMapper objectMapper) {
        super(postRepository, objectMapper, Post.class);
        this.postRepository = postRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional
    public List<Post> findAllByCursor(Long cursor, int size) {
        return postRepository.findAllByCursor(cursor, size);
    }
}
