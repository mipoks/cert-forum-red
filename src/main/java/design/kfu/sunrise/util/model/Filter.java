package design.kfu.sunrise.util.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Daniyar Zakiev
 */
@Getter
@Setter
@AllArgsConstructor
public class Filter {
    private Map<String, Set<String>> map;

    public Filter() {
        map = new HashMap();
    }

    public void addConstrait(String predicate, String value) {
        Set<String> predicates = map.get(predicate);
        if (predicates == null) {
            predicates = new HashSet<>();
        }
        predicates.add(value);
    }
}
