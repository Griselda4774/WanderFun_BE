package com.wanderfun.presentationlayer.controller;

import com.wanderfun.applicationlayer.dto.ResponseDto;
import com.wanderfun.applicationlayer.dto.posts.PostDto;
import com.wanderfun.applicationlayer.usecase.PostUsecase;
import com.wanderfun.presentationlayer.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<ResponseDto<List<PostDto>>> findAllByCursor(@RequestParam Long cursor, @RequestParam int size) {
        List<PostDto> result = postUsecase.findAllByCursor(cursor, size);
        if (result == null) {
            throw new RequestFailedException("Find all post by cursor failed!");
        }

        ResponseDto<List<PostDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all post by cursor successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
