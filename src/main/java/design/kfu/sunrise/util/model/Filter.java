package design.kfu.sunrise.util.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Filter {
    private boolean visible = true;
    private Long categoryId;
}
