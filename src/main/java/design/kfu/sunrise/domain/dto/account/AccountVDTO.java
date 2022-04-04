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
    private Long id;
    private String role;

    public static AccountVDTO from(Account account) {
        return AccountVDTO.builder()
                .id(account.getId())
                .role(account.getRole().name())
                .build();
    }

}
