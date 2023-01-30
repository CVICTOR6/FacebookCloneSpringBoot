package com.cvictor.facebookclonespringboot.service;


import com.cvictor.facebookclonespringboot.mapper.LikePosts;
import com.cvictor.facebookclonespringboot.model.User;
import com.cvictor.facebookclonespringboot.model.Post;

import java.util.List;

public interface PostService {
    void addPost(User user, Post post);

    List<LikePosts> getAllPost(User user);

    void updatePost(Post post);

    void deletePost(Post post);

    Post getPostById(Long id);
}
