package design.kfu.sunrise.domain.dto;

import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.embedded.ActiveInfo;
import design.kfu.sunrise.domain.model.embedded.CostInfo;
import design.kfu.sunrise.service.AccountService;
import design.kfu.sunrise.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Configurable(preConstruction = true)
public class ClubCDTO {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AccountService accountService;

    private Long id;
    private Long creatorId;
    private Long categoryId;
    private ActiveInfo activeInfo;

    @NotNull
    @Size(min = 5, max = 255)
    private String name;
    @NotNull
    @Size(min = 120, max = 4096)
    private String description;

    private CostInfo costInfo;

    public static Club toClub(ClubCDTO clubDTO) {
        return Club.builder()
                .name(clubDTO.getName())
                .category(clubDTO.categoryService.findOrThrow(clubDTO.getCategoryId()))
                .costInfo(clubDTO.costInfo)
                .creator(clubDTO.accountService.findOrThrow(clubDTO.getCreatorId()))
                .activeInfo(ActiveInfo.make(clubDTO.getActiveInfo()))
                .description(clubDTO.getDescription())
                .build();
    }

    public static ClubCDTO fromClub(Club club) {
        return ClubCDTO.builder()
                .activeInfo(club.getActiveInfo())
                .id(club.getId())
                .costInfo(club.getCostInfo())
                .creatorId(club.getCreator().getId())
                .categoryId(club.getCategory().getId())
                .name(club.getName())
                .description(club.getDescription())
                .build();
    }
}
