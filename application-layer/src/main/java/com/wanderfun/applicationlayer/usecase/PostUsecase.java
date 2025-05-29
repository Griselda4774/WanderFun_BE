package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.applicationlayer.dto.posts.CommentCreateDto;
import com.wanderfun.applicationlayer.dto.posts.CommentDto;
import com.wanderfun.applicationlayer.dto.posts.PostCreateDto;
import com.wanderfun.applicationlayer.dto.posts.PostDto;
import java.util.List;

public interface PostUsecase {
    List<PostDto> findAllPostByCursor(Long cursor, int size);
    PostDto findPostById(Long postId);
    boolean createPost(PostCreateDto postCreateDto, String accessToken);
    boolean updatePost(Long postId, PostCreateDto postDto, String accessToken);
    boolean deletePost(Long postId, String accessToken);
    List<CommentDto> findAllCommentByPostId(Long postId);
    boolean createComment(String accessToken, Long postId, CommentCreateDto commentCreateDto);
    boolean updateComment(String accessToken, Long commentId, CommentCreateDto commentCreateDto);
    boolean deleteComment(String accessToken, Long commentId);
}
