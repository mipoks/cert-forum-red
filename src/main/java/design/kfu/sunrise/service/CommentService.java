package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.model.Comment;
import design.kfu.sunrise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void editComment(Comment comment) {
        Optional<Comment> commentOptional = commentRepository.findById(comment.getId());
        if (commentOptional.isPresent()) {
            Comment savedComment = commentOptional.get();
            savedComment.setValue(comment.getValue());
        }
        commentRepository.save(comment);
    }

    public void deleteComment(Comment comment) {
        Optional<Comment> commentOptional = commentRepository.findById(comment.getId());
        if (commentOptional.isPresent()) {
            Comment savedComment = commentOptional.get();
            commentRepository.delete(savedComment);
        }
    }
}
