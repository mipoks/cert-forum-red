package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.dto.CommentDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.Comment;
import design.kfu.sunrise.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PreAuthorize("@access.hasAccessToWriteComment(#account, #club)")
    @GetMapping("/club/{club_id}/comment")
    public CommentDTO addComment(@PathVariable("club_id") Club club, @Valid CommentDTO commentDTO, @AuthenticationPrincipal(expression = "account") Account account){
        return commentService.addComment(commentDTO, club, account);
    }


    @PreAuthorize("@access.hasAccessToEditComment(#comment, #account) && @access.hasAccessToWriteComment(#account, #club)")
    @PutMapping("/club/{club_id}/comment/{comment_id}")
    public CommentDTO updateComment(@PathVariable("club_id") Club club, @Valid CommentDTO commentDTO, @PathVariable("comment_id") Comment comment, @AuthenticationPrincipal(expression = "account") Account account){
        return commentService.editAllComment(comment, commentDTO);
    }

}
