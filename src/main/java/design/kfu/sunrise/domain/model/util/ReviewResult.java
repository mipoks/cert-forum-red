package design.kfu.sunrise.domain.model.util;

import lombok.*;

import javax.validation.constraints.NotEmpty;

/**
 * @author Daniyar Zakiev
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResult {
    private String reason;
    @NotEmpty
    private boolean status;
}
