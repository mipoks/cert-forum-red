package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.dto.comment.CommentDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.Comment;
import design.kfu.sunrise.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
public class CommentController {

    private final CommentService commentService;


    @PreAuthorize("@access.hasAccessToWriteComment(#account, #club)")
    @PostMapping("/club/{club_id}/comment")
    public CommentDTO addComment(@PathVariable("club_id") Club club, @Valid @RequestBody CommentDTO commentDTO, @AuthenticationPrincipal(expression = "account") Account account){
        return CommentDTO.from(commentService.addComment(commentDTO, club, account));
    }


    @PreAuthorize("@access.hasAccessToEditComment(#comment, #account) && @access.hasAccessToWriteComment(#account, #club)")
    @PutMapping("/club/{club_id}/comment/{comment_id}")
    public CommentDTO updateComment(@PathVariable("club_id") Club club, @Valid CommentDTO commentDTO, @PathVariable("comment_id") Comment comment, @AuthenticationPrincipal(expression = "account") Account account){
        return CommentDTO.from(commentService.editAllComment(comment, commentDTO));
    }

    @PreAuthorize("@access.hasAccessToEditComment(#comment, #account) && @access.hasAccessToWriteComment(#account, #club)")
    @PostMapping("/club/{club_id}/{comment_id}/delete")
    public Boolean deleteComment(@PathVariable("club_id") Club club, @PathVariable("comment_id") Comment comment, @AuthenticationPrincipal(expression = "account") Account account){
        commentService.deleteComment(comment);
        return true;
    }
}
