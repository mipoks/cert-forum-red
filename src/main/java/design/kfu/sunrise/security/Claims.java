package design.kfu.sunrise.security;


import lombok.experimental.UtilityClass;

@UtilityClass
public class Claims {

    @UtilityClass
    public class Person {
        public final String NAME = "name";
        public final String EMAIL = "email";
        public final String ROLES = "roles";
        public final String FAMILY_NAME = "family_name";
    }
}