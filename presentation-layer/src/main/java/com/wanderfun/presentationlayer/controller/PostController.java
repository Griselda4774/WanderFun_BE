package com.wanderfun.presentationlayer.controller;

import com.wanderfun.applicationlayer.dto.ResponseDto;
import com.wanderfun.applicationlayer.dto.posts.PostCreateDto;
import com.wanderfun.applicationlayer.dto.posts.PostDto;
import com.wanderfun.applicationlayer.usecase.PostUsecase;
import com.wanderfun.presentationlayer.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wanderfun/post")
public class PostController {
    private final PostUsecase postUsecase;

    @Autowired
    public PostController(PostUsecase postUsecase) {
        this.postUsecase = postUsecase;
    }

    @GetMapping("/cursor")
    public ResponseEntity<ResponseDto<List<PostDto>>> findAllPostByCursor(@RequestParam Long cursor, @RequestParam int size) {
        List<PostDto> result = postUsecase.findAllPostByCursor(cursor, size);
        if (result == null) {
            throw new RequestFailedException("Find all post by cursor failed!");
        }

        ResponseDto<List<PostDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all post by cursor successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto<PostDto>> createPost(@RequestHeader("Authorization") String accessToken,
                                                        @RequestBody PostCreateDto postCreateDto) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        boolean result = postUsecase.createPost(postCreateDto, accessToken);
        if (!result) {
            throw new RequestFailedException("Create post failed!");
        }

        ResponseDto<PostDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.CREATED.toString());
        response.setMessage("Create post successful!");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<ResponseDto<PostDto>> updatePost(@PathVariable Long postId,
                                                          @RequestHeader("Authorization") String accessToken,
                                                          @RequestBody PostCreateDto postCreateDto) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        boolean result = postUsecase.updatePost(postId, postCreateDto, accessToken);
        if (!result) {
            throw new RequestFailedException("Update post failed!");
        }

        ResponseDto<PostDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Update post successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseDto<PostDto>> deletePost(@PathVariable Long postId,
                                                          @RequestHeader("Authorization") String accessToken) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        boolean result = postUsecase.deletePost(postId, accessToken);
        if (!result) {
            throw new RequestFailedException("Delete post failed!");
        }

        ResponseDto<PostDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete post successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
