package com.korit.study3.service;

import com.korit.study3.dao.PostDao;
import com.korit.study3.dto.AddPostReqDto;
import com.korit.study3.entity.Post;

import java.util.List;
import java.util.Optional;

public class PostService {
    private static PostService instance;
    private PostDao postDao;

    private PostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public static PostService getInstance() {
        if (instance == null) {
            instance = new PostService(PostDao.getInstance());
        }
        return instance;
    }

    public boolean isDuplicatedTitle(String title) {
        Optional<Post> foundPost = postDao.findPostByTitle(title);
        return foundPost.isPresent();
    }

    public int addPost(AddPostReqDto addPostReqDto) {
        return postDao.addPost(addPostReqDto.toEntity());
    }

    public Post getPostByPostId(Integer postId) {
        Optional<Post> foundPost = postDao.getPostByPostId(postId);
        return foundPost.orElse(null);
    }

    public List<Post> getPostListByUsername(String username) {
        return postDao.getPostListByUsername(username);
    }

    public List<Post> getPostListByKeyword(String keyword) {
        return postDao.getPostListByKeyword(keyword);
    }

}
