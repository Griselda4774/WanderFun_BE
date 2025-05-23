package com.wanderfun.infrastructurelayer.usecase;

import com.wanderfun.applicationlayer.dto.posts.PostCreateDto;
import com.wanderfun.applicationlayer.dto.posts.PostDto;
import com.wanderfun.applicationlayer.exception.NotHavePermissionException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.posts.PostService;
import com.wanderfun.applicationlayer.service.users.UserService;
import com.wanderfun.applicationlayer.usecase.PostUsecase;
import com.wanderfun.applicationlayer.util.JwtUtil;
import com.wanderfun.domainlayer.model.places.Place;
import com.wanderfun.domainlayer.model.posts.Post;
import com.wanderfun.domainlayer.model.trips.Trip;
import com.wanderfun.domainlayer.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PostUsecaseImpl implements PostUsecase {
    private final PostService postService;
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public PostUsecaseImpl(PostService postService, ObjectMapper objectMapper, JwtUtil jwtUtil, UserService userService) {
        this.postService = postService;
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    public List<PostDto> findAllPostByCursor(Long cursor, int size) {
        return objectMapper.mapList(postService.findAllByCursor(cursor, size), PostDto.class);
    }

    @Override
    public PostDto findPostById(Long postId) {
        return objectMapper.map(postService.findById(postId), PostDto.class);
    }

    @Override
    public boolean createPost(PostCreateDto postCreateDto, String accessToken) {
        Post post = objectMapper.map(postCreateDto, Post.class);
        post.setUser(new User());
        post.getUser().setId(jwtUtil.getIdFromToken(accessToken));
        postService.create(post);
        return true;
    }

    @Override
    public boolean updatePost(Long postId, PostCreateDto postCreateDto, String accessToken) {
        Post currentPost = postService.findById(postId);
        Post post = objectMapper.map(postCreateDto, Post.class);
        if (!Objects.equals(currentPost.getUser().getId(), jwtUtil.getIdFromToken(accessToken))) {
            throw new NotHavePermissionException("You don't have permission to update this post");
        }
        post.setUser(new User());
        post.getUser().setId(userService.findByAccountId(jwtUtil.getIdFromToken(accessToken)).getId());
        postService.updateById(postId, post);
        return true;
    }

    @Override
    public boolean deletePost(Long postId, String accessToken) {
        Post currentPost = postService.findById(postId);
        if (Objects.equals(currentPost.getUser().getId(), userService.findByAccountId(jwtUtil.getIdFromToken(accessToken)).getId())) {
            postService.deleteById(postId);
        }
        return true;
    }

    private void checkPost(Post post, PostCreateDto postCreateDto) {
        if (post.isTripShare()) {
            post.setTrip(new Trip());
            post.getTrip().setId(postCreateDto.getTripId());
            post.setPlace(null);
            post.setImage(null);
        } else {
            post.setTrip(null);
            if (post.getPlace() != null) {
                post.setPlace(new Place());
                post.getPlace().setId(postCreateDto.getPlaceId());
            }
        }
    }
}
