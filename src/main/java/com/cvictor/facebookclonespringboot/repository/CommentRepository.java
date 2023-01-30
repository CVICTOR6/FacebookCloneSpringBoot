package com.cvictor.facebookclonespringboot.repository;

import com.cvictor.facebookclonespringboot.model.Comment;
import com.cvictor.facebookclonespringboot.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByCommentId(Long id);
    List<Comment> findCommentByPost(Post post);

    void deleteCommentByCommentId(Long commentId);

    void deleteAllByPost(Post post);
}
