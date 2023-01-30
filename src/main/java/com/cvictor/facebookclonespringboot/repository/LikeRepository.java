package com.cvictor.facebookclonespringboot.repository;

import com.cvictor.facebookclonespringboot.model.Post;
import com.cvictor.facebookclonespringboot.model.PostLikes;
import com.cvictor.facebookclonespringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LikeRepository extends JpaRepository<PostLikes, Long> {
    @Transactional
    void deletePostLikesByPostAndUser(Post post, User user);

    List<PostLikes> findAllByPost(Post post);

    void deleteAllByPost(Post post);
}
