package design.kfu.sunrise.domain.dto.club;

import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.embedded.ClubInfo;
import design.kfu.sunrise.domain.model.embedded.CostInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClubVDTO {

    private Long id;

    private String name;

    private String description;

    private ClubInfo activeInfo;

    private CostInfo costInfo;

    private Instant created;

    private Instant updated;

    private String authorId;

    private String categoryName;

    public static ClubVDTO from(Club club) {
        return ClubVDTO.builder()
                .id(club.getId())
                .name(club.getName())
                .description(club.getDescription())
                .activeInfo(club.getClubInfo())
                .costInfo(club.getCostInfo())
                .created(club.getCreated())
                .updated(club.getUpdated())
                .authorId(club.getAuthor() == null ? null : club.getAuthor().getId())
                .categoryName(club.getCategory() == null ? null : club.getCategory().getName())
                .build();
    }
}
