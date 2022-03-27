package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.CommentDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.Comment;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface CommentService {
    @Transactional
    CommentDTO addComment(CommentDTO commentDTO, Club club, Account account);

    @Transactional
    void editComment(Comment comment);

    @Transactional
    void deleteComment(Comment comment);

    @Transactional
    Set<Comment> getComments(Club club);

    CommentDTO editAllComment(Comment comment, CommentDTO commentDTO);
}
