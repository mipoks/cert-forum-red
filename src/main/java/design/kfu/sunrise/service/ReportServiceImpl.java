package design.kfu.sunrise.service;

import design.kfu.sunrise.exception.ErrorType;
import design.kfu.sunrise.exception.Exc;
import design.kfu.sunrise.exchange.kafka.UserReportProducer;
import design.kfu.sunrise.security.TokenUser;
import design.kfu.sunrise.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.cert.commons.model.ReportDto;
import ru.itis.cert.commons.model.UserDto;
@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final AccountService accountService;
    private final UserReportProducer userReportProducer;

    @Override
    public ReportDto getReport() {
        TokenUser tokenUser = SecurityUtils.getUser().orElseThrow(Exc.sup(ErrorType.UNEXPECTED_ERROR, "Отсутствует пользователь"));
        log.info("tokenUser {}",tokenUser);
        UserDto userDto = UserDto.builder()
                .email(tokenUser.getEmail())
                .name(tokenUser.getName())
                .role(tokenUser.getRoles().stream().filter(s->s.startsWith("ROLE")).findFirst().orElse("ANONYMOUS"))
                .surname(tokenUser.getSurname())
                .build();
        return userReportProducer.getUserReport(userDto);
    }
}
