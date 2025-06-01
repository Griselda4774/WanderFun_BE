package com.wanderfun.infrastructurelayer.usecase;

import com.wanderfun.applicationlayer.dto.posts.CommentCreateDto;
import com.wanderfun.applicationlayer.dto.posts.CommentDto;
import com.wanderfun.applicationlayer.dto.posts.PostCreateDto;
import com.wanderfun.applicationlayer.dto.posts.PostDto;
import com.wanderfun.applicationlayer.exception.NotHavePermissionException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.posts.CommentService;
import com.wanderfun.applicationlayer.service.posts.PostService;
import com.wanderfun.applicationlayer.service.users.UserService;
import com.wanderfun.applicationlayer.usecase.PostUsecase;
import com.wanderfun.applicationlayer.util.JwtUtil;
import com.wanderfun.domainlayer.model.places.Place;
import com.wanderfun.domainlayer.model.posts.Comment;
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
    private final CommentService commentService;

    @Autowired
    public PostUsecaseImpl(PostService postService, ObjectMapper objectMapper, JwtUtil jwtUtil, UserService userService, CommentService commentService) {
        this.postService = postService;
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.commentService = commentService;
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
        postCreateDto.setTripShare(postCreateDto.getTripId() != null);
        Post post = objectMapper.map(postCreateDto, Post.class);
        post.setUser(new User());
        post.getUser().setId(userService.findByAccountId(jwtUtil.getIdFromToken(accessToken)).getId());
        postService.create(post);
        return true;
    }

    @Override
    public boolean updatePost(Long postId, PostCreateDto postCreateDto, String accessToken) {
        postCreateDto.setTripShare(postCreateDto.getTripId() != null);
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

    @Override
    public List<CommentDto> findAllCommentByPostId(String accessToken, Long postId) {
        return objectMapper.mapList(commentService.findAllByPostId(postId), CommentDto.class);
    }

    @Override
    public CommentDto createComment(String accessToken, Long postId, CommentCreateDto commentCreateDto) {
        Comment comment = objectMapper.map(commentCreateDto, Comment.class);
        comment.setPostId(postId);
        comment.setUser(new User());
        comment.getUser().setId(userService.findByAccountId(jwtUtil.getIdFromToken(accessToken)).getId());

        Comment savedComment = commentService.create(comment);
        Comment retrievedComment = commentService.findById(savedComment.getId());

        return objectMapper.map(retrievedComment, CommentDto.class);
    }

    @Override
    public CommentDto updateComment(String accessToken, Long commentId, CommentCreateDto commentCreateDto) {
        Comment currentComment = commentService.findById(commentId);
        Comment comment = objectMapper.map(commentCreateDto, Comment.class);
        if (!Objects.equals(currentComment.getUser().getId(), jwtUtil.getIdFromToken(accessToken))) {
            throw new NotHavePermissionException("You don't have permission to update this comment");
        }
        comment.setUser(new User());
        comment.getUser().setId(userService.findByAccountId(jwtUtil.getIdFromToken(accessToken)).getId());

        Comment savedComment = commentService.updateById(commentId, comment);
        Comment retrievedComment = commentService.findById(savedComment.getId());

        return objectMapper.map(retrievedComment, CommentDto.class);
    }

    @Override
    public boolean deleteComment(String accessToken, Long commentId) {
        Comment currentComment = commentService.findById(commentId);
        if (Objects.equals(currentComment.getUser().getId(), userService.findByAccountId(jwtUtil.getIdFromToken(accessToken)).getId())) {
            commentService.deleteById(commentId);
        }
        return true;
    }
}
