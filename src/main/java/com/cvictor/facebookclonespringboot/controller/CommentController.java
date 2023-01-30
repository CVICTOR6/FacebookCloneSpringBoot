package com.cvictor.facebookclonespringboot.controller;

import com.cvictor.facebookclonespringboot.model.Comment;
import com.cvictor.facebookclonespringboot.model.Post;
import com.cvictor.facebookclonespringboot.model.User;
import com.cvictor.facebookclonespringboot.repository.PostRepository;
import com.cvictor.facebookclonespringboot.service.CommentService;
import com.cvictor.facebookclonespringboot.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;

    @GetMapping("/editcomment")
    public String getEditCommentPage(Model model, HttpSession httpSession, HttpServletRequest request, Long commentId) {
        User user = (User) httpSession.getAttribute("logUser");
        if (user==null) return "redirect:/";

        Comment comment = commentService.getCommentById(commentId);

        model.addAttribute("editcomment", comment);
        model.addAttribute("loggeduser", user);

        return "editcomment";
    }

    @GetMapping("/comments")
    public String getAllCommentsPage(Model model, HttpServletRequest request, HttpSession httpSession, Long postId) {
        User user = (User) httpSession.getAttribute("logUser");
        if (user==null) return "redirect:/";

        Post post = postService.getPostById(postId);
        List<Comment> comments = commentService.findCommentByPost(post);
        model.addAttribute("allComments", comments);
        model.addAttribute("loggedUser", user);
        model.addAttribute("commentDelete", new Comment());
        return "comments";
    }


    @PostMapping("/home_comment")
    public String createComment(HttpSession httpSession, HttpServletRequest request, @ModelAttribute("newComment") Comment comment) {
        User user = (User) httpSession.getAttribute("logUser");
        if (user==null) return "redirect:/";

        Long postId = Long.parseLong(request.getParameter("postid"));

        Post post = postService.getPostById(postId);
        Comment newComment = new Comment(comment.getCommentBody(), post, user);
        commentService.saveComment(newComment);
        postService.getPostById(postId).getListOfComments().add(newComment);
        return "redirect:/home";
    }


    @PostMapping("/update_comment")
    public String updateComment(@ModelAttribute Comment comment){
        System.err.println("in edit comment");
        System.err.println(comment);
       // Post newPost = postService.getPostById(post.getPostId());
        Comment newComment = commentService.getCommentById(comment.getCommentId());
        newComment.setCommentBody(comment.getComments());
        commentService.updateComment(newComment);

        return "redirect:/home";

    }


    @PostMapping("/delete_comment")
    public String deleteComment(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("commentDelete") Comment comment) {

        commentService.deleteComment(comment.getCommentId());
        return "redirect:/home";
    }
}
