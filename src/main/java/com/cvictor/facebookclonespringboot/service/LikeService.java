package com.cvictor.facebookclonespringboot.service;

import com.cvictor.facebookclonespringboot.model.Post;
import com.cvictor.facebookclonespringboot.model.User;

public interface LikeService {
    boolean likePost(User user, Long postId, String action);
    void deleteAllLikesInPost(Post post);
}
