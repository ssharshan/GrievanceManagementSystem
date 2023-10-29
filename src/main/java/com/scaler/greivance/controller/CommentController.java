package com.scaler.greivance.controller;

import com.scaler.greivance.dto.UserCommentDTO;
import com.scaler.greivance.exception.NotFoundException;
import com.scaler.greivance.model.Comment;
import com.scaler.greivance.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/grievances")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{grievanceId}/comments")
    public List<UserCommentDTO> getAllComments() {
        return this.commentService.getAllComments();
    }

    @GetMapping("/{grievanceId}/comments/{id}")
    public UserCommentDTO getCommentById(@PathVariable UUID grievanceId, @PathVariable UUID id) throws NotFoundException {
        return this.commentService.getCommentById(id);
    }

    @PostMapping("/{grievanceId}/comments")
    public UserCommentDTO saveComment(@PathVariable UUID grievanceId, @RequestBody UserCommentDTO comment) {
        return this.commentService.saveComment(comment);
    }

    @PutMapping("/{grievanceId}/comments/{id}")
    public UserCommentDTO updateCommentById(@PathVariable UUID grievanceId,@PathVariable UUID id, @RequestBody UserCommentDTO comment) {
        return this.commentService.updateCommentById(id, comment);
    }

    @DeleteMapping("/{grievanceId}/comments/{id}")
    public void deleteCommentById(@PathVariable UUID grievanceId, @PathVariable UUID id) {
        this.commentService.deleteCommentById(id);
    }
}
