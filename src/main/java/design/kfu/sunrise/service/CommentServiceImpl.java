package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.CommentDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.Comment;
import design.kfu.sunrise.exception.ErrorType;
import design.kfu.sunrise.exception.Exc;
import design.kfu.sunrise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ClubService clubService;

    public Comment addComment(CommentDTO commentDTO, Club club, Account account) {
        Comment comment = commentRepository.save(CommentDTO.toComment(commentDTO, club, account));
        club.getComments().add(comment);
        clubService.updateComments(club);
        return comment;
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

    public Set<Comment> getComments(Club club) {
        return club.getComments();
    }

    @Override
    public Comment editAllComment(Comment comment, CommentDTO commentDTO) {
        comment.setValue(commentDTO.getValue());
        return commentRepository.save(comment);
    }

    @Override
    public Comment findOrNull(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @Override
    public Comment findOrThrow(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(Exc.sup(ErrorType.ENTITY_NOT_FOUND,"Сущность комментария не найдена"));
    }
}
