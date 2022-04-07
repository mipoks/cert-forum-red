package design.kfu.sunrise.domain.model.util;

import lombok.*;

/**
 * @author Daniyar Zakiev
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    private String name;
    private String description;
    private String url;
}
