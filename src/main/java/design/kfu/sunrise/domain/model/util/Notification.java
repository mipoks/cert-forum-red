package design.kfu.sunrise.domain.model.util;

import lombok.*;

/**
 * @author Daniyar Zakiev
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    private String name;
    private String description;
    private String url;
}
