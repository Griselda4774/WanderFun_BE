package com.wanderfun.infrastructurelayer.repository.posts;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.posts.Post;
import com.wanderfun.domainlayer.model.trips.Trip;
import com.wanderfun.domainlayer.repository.posts.PostRepository;
import com.wanderfun.infrastructurelayer.persistence.entity.posts.PostEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.posts.JpaPostRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepositoryImpl extends BaseRepositoryImpl<Post, PostEntity, Long> implements PostRepository {
    private final JpaPostRepository jpaPostRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public PostRepositoryImpl(JpaPostRepository jpaPostRepository, ObjectMapper objectMapper) {
        super(jpaPostRepository, objectMapper, Post.class, PostEntity.class);
        this.jpaPostRepository = jpaPostRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Post save(Post post) {
        PostEntity postEntity = objectMapper.map(post, PostEntity.class);
        if (post.isTripShare()) {
            postEntity.setPlace(null);
        } else {
            postEntity.setTrip(null);
        }

        PostEntity savedPostEntity = jpaPostRepository.save(postEntity);

        return objectMapper.map(savedPostEntity, Post.class);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return jpaPostRepository.findById(id)
                .map(postEntity -> {
                    Post post = objectMapper.map(postEntity, Post.class);
//                    post.setCommentCount(jpaPostRepository.countCommentById(post.getId()));
//                    post.setLikeCount(jpaPostRepository.countLikeById(post.getId()));
                    return post;
                });
    }

    @Override
    public List<Post> findAllByCursor(Long cursor, int size) {
        Pageable pageable = PageRequest.of(0, size);
        List<Post> postList = objectMapper.mapList(jpaPostRepository.findAllPostByCursor(cursor, pageable), Post.class);
//        postList.forEach(post -> {
//            post.setCommentCount(jpaPostRepository.countCommentById(post.getId()));
//            post.setLikeCount(jpaPostRepository.countLikeById(post.getId()));
//        });
        return postList;
    }

    @Override
    public List<Post> findAllByUserId(Long userId) {
        List<PostEntity> postEntities = jpaPostRepository.findAllByUserId(userId);
        List<Post> posts = objectMapper.mapList(postEntities, Post.class);
//        posts.forEach(post -> {
//            post.setCommentCount(jpaPostRepository.countCommentById(post.getId()));
//            post.setLikeCount(jpaPostRepository.countLikeById(post.getId()));
//        });
        return posts;
    }
}
