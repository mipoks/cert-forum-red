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
public class AccountCDTO {
    private String password;
    private String email;
    private String phone;

    public static Account toAccount(AccountCDTO accountCDTO) {
        return Account.builder()
                .phone(accountCDTO.getPhone())
                .hashPassword(accountCDTO.getPassword())
                .login(accountCDTO.getEmail())
                .build();
    }
}
