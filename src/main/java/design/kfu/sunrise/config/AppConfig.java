package design.kfu.sunrise.config;

import design.kfu.sunrise.service.StaticService;
import design.kfu.sunrise.util.converter.LongToAccountConverter;
import design.kfu.sunrise.util.converter.LongToClubConverter;
import org.springframework.context.ApplicationContext;
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

    //ToDo проверить, возможно, такие конвертеры используются Spring Boot по умолчанию
    @Bean
    public LongToAccountConverter accountIdToAccountConverter() {
        return new LongToAccountConverter();
    }
    @Bean
    public LongToClubConverter clubIdToClubConverter() {
        return new LongToClubConverter();
    }

    @Bean
    public StaticService getStaticService(ApplicationContext context) {
        return new StaticService(context);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(accountIdToAccountConverter());
        registry.addConverter(clubIdToClubConverter());
    }

}
