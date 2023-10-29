package com.scaler.greivance.service;

import com.scaler.greivance.dto.UserCommentDTO;
import com.scaler.greivance.exception.NotFoundException;
import com.scaler.greivance.model.Comment;
import com.scaler.greivance.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<UserCommentDTO> getAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(comment -> toUserCommentDTO(comment))
                .toList();
    }

    public UserCommentDTO getCommentById(UUID id) throws NotFoundException {
        Comment comment = commentRepository.findById(id)
                                            .orElseThrow(() -> new NotFoundException("Comment not found"));

//        if(comment != null) {
            return toUserCommentDTO(comment);
//        }
//        return null;
    }

    public UserCommentDTO saveComment(UserCommentDTO comment) {
        Comment commentToSave = toComment(comment);
        return toUserCommentDTO(commentRepository.save(commentToSave));
    }

    public void deleteCommentById(UUID id) {
        commentRepository.deleteById(id);
    }

    public UserCommentDTO updateCommentById(UUID id, UserCommentDTO comment) {
        Comment commentToUpdate = commentRepository.findById(id).orElse(null);
        if (commentToUpdate != null) {
            commentToUpdate.setComment(comment.getComment());
            return toUserCommentDTO(commentRepository.save(commentToUpdate));
        }
        return null;
    }

    public UserCommentDTO toUserCommentDTO(Comment comment) {
        UserCommentDTO userCommentDTO = new UserCommentDTO();
        userCommentDTO.setId(comment.getId());
        userCommentDTO.setComment(comment.getComment());
        return userCommentDTO;
    }

    public Comment toComment(UserCommentDTO userCommentDTO) {
        Comment comment = new Comment();
        comment.setId(userCommentDTO.getId());
        comment.setComment(userCommentDTO.getComment());
        return comment;
    }

}
