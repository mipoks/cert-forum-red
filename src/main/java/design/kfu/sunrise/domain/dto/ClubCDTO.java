package design.kfu.sunrise.domain.dto;

import design.kfu.sunrise.domain.model.Club;
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

    @NotNull
    @Size(min = 5, max = 255)
    private String name;
    @NotNull
    @Size(min = 120, max = 4096)
    private String description;

    public static Club toClub(ClubCDTO clubDTO) {
        return Club.builder()
                .name(clubDTO.getName())
                .description(clubDTO.getDescription())
                .build();
    }

    public static ClubCDTO fromClub(Club club) {
        return ClubCDTO.builder()
                .name(club.getName())
                .description(club.getDescription())
                .build();
    }
}
