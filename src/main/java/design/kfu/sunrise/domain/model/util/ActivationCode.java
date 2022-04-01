package design.kfu.sunrise.domain.model.util;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * @author Daniyar Zakiev
 */
@Builder
@Data
@RedisHash(value = "ActivationCode", timeToLive = 300)
public class ActivationCode implements Serializable {

    private Long accountId;
    @Id
    private String code;
}
