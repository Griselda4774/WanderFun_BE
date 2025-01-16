package com.project2.wanderfun.presentation.controller;

import com.project2.wanderfun.application.dto.ResponseDto;
import com.project2.wanderfun.application.dto.album.AlbumCreateDto;
import com.project2.wanderfun.application.dto.album.AlbumDto;
import com.project2.wanderfun.application.usecase.AlbumUsecase;
import com.project2.wanderfun.domain.model.Album;
import com.project2.wanderfun.presentation.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wanderfun/album")
public class AlbumController {
    private final AlbumUsecase albumUsecase;

    @Autowired
    public AlbumController(AlbumUsecase albumUsecase) {
        this.albumUsecase = albumUsecase;
    }

    @GetMapping("")
    public ResponseEntity<ResponseDto<List<AlbumDto>>> findAllAlbums(@RequestHeader("Authorization") String accessToken) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        List<AlbumDto> result = albumUsecase.findAllAlbums(accessToken);
        if(result == null) {
            throw new RequestFailedException("Find all albums failed!");
        }

        ResponseDto<List<AlbumDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all albums successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<AlbumDto>> findAlbumById(@PathVariable long id) {
        AlbumDto result = albumUsecase.findAlbumById(id);
        if (result == null) {
            throw new RequestFailedException("Find album failed!");
        }

        ResponseDto<AlbumDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find album successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto<AlbumDto>> createAlbum(@RequestHeader("Authorization") String accessToken,
                                                     @RequestBody AlbumCreateDto albumCreateDto) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        boolean result = albumUsecase.createAlbum(albumCreateDto, accessToken);
        if (!result) {
            throw new RequestFailedException("Create album failed!");
        }

        ResponseDto<AlbumDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Create album successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<AlbumDto>> updateAlbumById(@PathVariable long id,
                                                         @RequestBody AlbumCreateDto albumCreateDto) {
        boolean result = albumUsecase.updateAlbumById(id, albumCreateDto);
        if (!result) {
            throw new RequestFailedException("Update album failed!");
        }

        ResponseDto<AlbumDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Update album successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<AlbumDto>> deleteAlbumById(@PathVariable long id) {
        boolean result = albumUsecase.deleteAlbumById(id);
        if (!result) {
            throw new RequestFailedException("Delete album failed!");
        }

        ResponseDto<AlbumDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete album successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseDto<AlbumDto>> deleteAllAlbums(@RequestHeader("Authorization") String accessToken) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }
        boolean result = albumUsecase.deleteAllAlbums(accessToken);
        if (!result) {
            throw new RequestFailedException("Delete all albums failed!");
        }

        ResponseDto<AlbumDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete all albums successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}