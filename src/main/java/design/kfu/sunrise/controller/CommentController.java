package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.dto.AccountCDTO;
import design.kfu.sunrise.domain.dto.AccountVDTO;
import design.kfu.sunrise.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final AccountService accountService;

    @GetMapping("/comment/{club_id}")
    @PreAuthorize("@access.hasAccessToReadComment(#userId,"FIND_SHADOW_PAGES")")
    public ResponseEntity<?> getShadowPages(@PathVariable("user_id") Long userId){
        //todo сервис вызова функции
        return ResponseEntity.ok("200");
    }
}
