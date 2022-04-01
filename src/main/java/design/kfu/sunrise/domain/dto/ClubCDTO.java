package design.kfu.sunrise.domain.dto;

import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.embedded.ActiveInfo;
import design.kfu.sunrise.domain.model.embedded.CostInfo;
import design.kfu.sunrise.service.StaticService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClubCDTO {

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
                .category(StaticService.getCategoryService().findOrThrow(clubDTO.getCategoryId()))
                .costInfo(clubDTO.costInfo)
                .creator(StaticService.getAccountService().findOrThrow(clubDTO.getCreatorId()))
                .activeInfo(clubDTO.getActiveInfo())
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
