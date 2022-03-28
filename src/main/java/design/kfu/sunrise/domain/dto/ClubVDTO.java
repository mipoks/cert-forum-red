package design.kfu.sunrise.domain.dto;

import design.kfu.sunrise.domain.model.Club;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Daniyar Zakiev
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClubVDTO {
    private Long id;
    private String name;
    private String description;

    public static ClubVDTO from(Club club) {
        return ClubVDTO.builder()
                .name(club.getName())
                .description(club.getDescription())
                .id(club.getId())
                .build();
    }
}
