package design.kfu.sunrise.domain.dto.club;

import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.embedded.ActiveInfo;
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

/**
 * @author Daniyar Zakiev
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "clubind")
public class ClubVDTO {

    private Long id;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    private ActiveInfo activeInfo;

    @Transient
    private CostInfo costInfo;

    @Transient
    private Instant created;

    @Transient
    private Instant updated;

    @Transient
    private Long authorId;

    @Transient
    private String categoryName;

    public static ClubVDTO from(Club club) {
        return ClubVDTO.builder()
                .id(club.getId())
                .name(club.getName())
                .description(club.getDescription())
                .activeInfo(club.getActiveInfo())
                .costInfo(club.getCostInfo())
                .created(club.getCreated())
                .updated(club.getUpdated())
                .authorId(club.getAuthor().getId())
                .categoryName(club.getCategory().getName())
                .build();
    }
}
