package design.kfu.sunrise.domain.dto;

import design.kfu.sunrise.domain.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountVDTO {
    private String email;
    private String phone;

    public static AccountVDTO from(Account account) {
        return AccountVDTO.builder()
                .email(account.getLogin())
                .phone(account.getPhone())
                .build();
    }

}
