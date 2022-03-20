package design.kfu.sunrise.domain.dto;

import design.kfu.sunrise.domain.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountCDTO {
    @NotNull
    @Size(min = 6, max = 25)
    private String password;
    @Email
    private String email;
    @NotNull
    @Size(min = 8, max = 15)
    private String phone;

    public static Account toAccount(AccountCDTO accountCDTO) {
        return Account.builder()
                .phone(accountCDTO.getPhone())
                .hashPassword(accountCDTO.getPassword())
                .login(accountCDTO.getEmail())
                .build();
    }
}
