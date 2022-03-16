package design.kfu.sunrise.config;

import design.kfu.sunrise.util.converter.LongToAccountConverter;
import design.kfu.sunrise.util.converter.LongToClubConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LongToAccountConverter  accIdtoAccountConverter() {
        return new LongToAccountConverter();
    }
    @Bean
    public LongToClubConverter  clubIdtoClubConverter() {
        return new LongToClubConverter();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(accIdtoAccountConverter());
        registry.addConverter(clubIdtoClubConverter());
    }

}
