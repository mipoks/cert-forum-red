package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.dto.ClubCDTO;
import design.kfu.sunrise.domain.dto.ClubVDTO;
import design.kfu.sunrise.domain.dto.CommentDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.service.ClubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Daniyar Zakiev
 */
@Slf4j
@RestController
public class ClubController {

    private ClubService clubService;

    @PermitAll
    @GetMapping("/club/{club_id}")
    public ClubVDTO getClub(@PathVariable("club_id") Club club) {
        return ClubVDTO.fromClub(club);
    }

    @PreAuthorize("@access.hasAccessToReadComment(#club, #account)")
    @GetMapping("/club/{club_id}/comments")
    public List<CommentDTO> getClubComments(@PathVariable("club_id") Club club, @AuthenticationPrincipal(expression = "account") Account account) {
        return club.getComments().stream().map(CommentDTO::fromComment).collect(Collectors.toList());
    }

    @PreAuthorize("@access.hasAccessToCreateClub(#account)")
    @PostMapping("/club")
    public ClubVDTO addPost(@Valid ClubCDTO clubDTO, @AuthenticationPrincipal(expression = "account") Account account){
        return clubService.addClub(clubDTO);
    }

    @PreAuthorize("@access.hasAccessToEnterClub(#account, #club)")
    @PostMapping("/club/{club_id}")
    public ClubVDTO addPost(@PathVariable("club_id") Club club, @AuthenticationPrincipal(expression = "account") Account account) {
        clubService.addAccountToClub(club, account);
        return ClubVDTO.fromClub(club);
    }

}