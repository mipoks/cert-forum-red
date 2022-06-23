package design.kfu.sunrise.domain.dto.account;

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
    private String id;
    private String role;
    private String username;

    public static AccountVDTO from(Account account) {
        return AccountVDTO.builder()
                .username(account.getEmail())
                .id(account.getId())
                .build();
    }

}
