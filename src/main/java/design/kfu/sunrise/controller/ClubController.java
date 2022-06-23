package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.dto.club.ClubCDTO;
import design.kfu.sunrise.domain.dto.club.ClubVDTO;
import design.kfu.sunrise.domain.dto.comment.CommentDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.service.ClubService;
import design.kfu.sunrise.service.access.AccountAccessService;
import design.kfu.sunrise.util.model.Filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/v1/club")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @PermitAll
    @GetMapping("/{club_id}")
    public ClubVDTO getClub(@PathVariable("club_id") Club club) {
        return ClubVDTO.from(club);
    }

    @PermitAll
    @GetMapping("/{club_id}/account")
    public boolean isAccountInClub(@PathVariable("club_id") Club club, @RequestBody Account account) {
        return club.getAccounts().contains(account);
    }

    @PreAuthorize("@access.hasAccessToReadComment(#club, #account)")
    @GetMapping("/{club_id}/comments")
    public List<CommentDTO> getClubComments(@PathVariable("club_id") Club club, @RequestBody Account account) {
        return club.getComments().stream().map(CommentDTO::from).collect(Collectors.toList());
    }

    @PreAuthorize("@access.hasAccessToCreateClub()")
    @PostMapping("account/{account_id}")
    public ClubVDTO addClub(@Valid @RequestBody ClubCDTO clubDTO,@PathVariable("account_id")String accountId) {
        clubDTO.setAuthorId(accountId);
        log.info("clubDTO {}",clubDTO);
        return ClubVDTO.from(clubService.addClub(clubDTO));
    }

    @Transactional
    @PreAuthorize("@access.hasAccessToEnterClub(#account, #club)")
    @PostMapping("/{club_id}")
    public ClubVDTO enterClub(@PathVariable("club_id") Club club, @RequestBody  Account account) {
        clubService.addAccountToClub(club, account);
        return ClubVDTO.from(club);
    }

    @PreAuthorize("@access.hasAccessToEditClub(#account, #club)")
    @PutMapping("/{clubId}")
    public ClubVDTO updateClub(@PathVariable("clubId") Club club, @Valid @RequestBody ClubCDTO clubDTO, @RequestBody Account account) {
        club.setDescription(clubDTO.getDescription());
        club.setName(clubDTO.getName());
        club.setClubInfo(clubDTO.getClubInfo());
        return ClubVDTO.from(clubService.updateClub(club));
    }


    @GetMapping("/clubs")
    public Set<ClubVDTO> getClubs(@RequestParam("category_id") Long categoryId) {
        Filter filter = Filter.builder()
                .categoryId(categoryId)
                .build();
        return clubService.findClubs(filter).stream().map(ClubVDTO::from).collect(Collectors.toSet());
    }
}
