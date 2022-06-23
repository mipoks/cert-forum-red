package design.kfu.sunrise.domain.model.embedded;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Builder
public class ClubsInfo implements Serializable {

    @Column(name = "ACCOUNT_ID")
    private String dAccountId;

    @Column(name = "CLUB_ID")
    private Long clubId;

    public ClubsInfo() {

    }

    public ClubsInfo(String accountId, Long clubId) {
        this.dAccountId = accountId;
        this.clubId = clubId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubsInfo clubsInfo = (ClubsInfo) o;
        return Objects.equals(clubId, clubsInfo.clubId) && Objects.equals(dAccountId, clubsInfo.dAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubId, dAccountId);
    }

    @Column(name = "ACCOUNT_ID")
    public String getAccountId() {
        return dAccountId;
    }

    public void setAccountId(String accountId) {
        this.dAccountId = accountId;
    }


    @Column(name = "CLUB_ID")
    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }
}
