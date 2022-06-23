package design.kfu.sunrise.util;

import com.nimbusds.jose.shaded.json.JSONArray;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class JwtClaimUtils {

    /**
     * Извлекает список из claim мапы
     */
    public List<String> getClaimAsListString(Map<String, Object> claims, String claim) {
        return ((JSONArray) claims.getOrDefault(claim, new JSONArray()))
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    /**
     * Извлекает строковое значние из claim мапы
     */
    public String getClaimAsString(Map<String, Object> claims, String claim) {
        return (String) claims.getOrDefault(claim, "");
    }
}