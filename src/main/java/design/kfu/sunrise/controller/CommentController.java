package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.service.AccountService;
import design.kfu.sunrise.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final AccountService accountService;

    private final CommentService commentService;

    //ToDo протестировать
    @GetMapping("/comment/{club_id}")
    @PreAuthorize("@access.hasAccessToReadComment(#club, #account)")
    public ResponseEntity<?> getClubComments(@PathVariable("club_id") Club club, @AuthenticationPrincipal(expression = "account") Account account){
        return ResponseEntity.ok(commentService.getComments(club));
    }


}
