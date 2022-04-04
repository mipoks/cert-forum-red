package design.kfu.sunrise.util.model;

import lombok.*;

/**
 * @author Daniyar Zakiev
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModelEvent<T> {
    private T model;
    private String event;
}
