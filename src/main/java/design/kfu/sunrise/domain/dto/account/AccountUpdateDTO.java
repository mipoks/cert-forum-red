package design.kfu.sunrise.domain.dto.account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class AccountUpdateDTO {
    @Deprecated
    @NotNull
    @Size(min = 6, max = 25)
    private String newPassword;

    @Deprecated
    @NotNull
    @Size(min = 6, max = 25)
    private String oldPassword;

    @Email
    private String email;

    @NotNull
    @Size(min = 8, max = 15)
    private String phone;
}
