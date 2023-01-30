package com.cvictor.facebookclonespringboot.service;


import com.cvictor.facebookclonespringboot.model.Comment;
import com.cvictor.facebookclonespringboot.model.Post;

import java.util.List;

public interface CommentService {
    void updateComment(Comment editedComment);

//    void deleteComment(Comment comment);

    Comment getCommentById(Long id);

    void saveComment(Comment comment);

    List<Comment> findCommentByPost(Post post);

    void deleteComment(Long commentId);

    void deleteAllCommentsInPost(Post post);

}
