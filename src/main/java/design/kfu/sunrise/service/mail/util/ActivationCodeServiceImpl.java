package design.kfu.sunrise.service.mail.util;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.util.ActivationCode;
import design.kfu.sunrise.exception.ErrorType;
import design.kfu.sunrise.exception.Exc;
import design.kfu.sunrise.repository.util.ActivationCodeRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

@Deprecated
@Service
public class ActivationCodeServiceImpl implements ActivationCodeService {

    @Autowired
    private ActivationCodeRepository activationCodeRepository;

    private MessageDigest digest;

    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringBuffer.toString();
    }

    @PostConstruct
    private void initDigest() {
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            Exc.gen(ErrorType.UNEXPECTED_ERROR);
        }
    }

    @Override
    public ActivationCode generate(Account account) {
        ActivationCode ac = ActivationCode.builder()
                .accountId(account.getId())
                .code(generateCode(account.getEmail()))
                .build();
        return ac;
    }

    @Override
    public ActivationCode save(ActivationCode code) {
        return activationCodeRepository.save(code);
    }

    private String generateCode(String email) {
        byte[] hashedBytes = digest.digest((email + Instant.now()).getBytes(StandardCharsets.UTF_8));
        return convertByteArrayToHexString(hashedBytes);
    }
}
