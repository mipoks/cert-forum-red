package design.kfu.sunrise.domain.dto;

import design.kfu.sunrise.domain.model.Club;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClubDTO {

    private String name;
    private String description;

    public static Club toClub(ClubDTO commentDTO) {
        return Club.builder()
                .name(commentDTO.getName())
                .description(commentDTO.getDescription())
                .build();
    }
}
