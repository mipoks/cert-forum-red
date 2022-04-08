package design.kfu.sunrise.domain.model.util;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;

/**
 * @author Daniyar Zakiev
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash(value = "Review", timeToLive = 60 * 60 * 24 * 30 * 2 /* 60 суток */)
public class Review {
    @Id
    private Long id;

    private String objectName;
    private Long objectId;
    private Instant instant;

    @NotEmpty
    private boolean accept;
    private String reason;
}
