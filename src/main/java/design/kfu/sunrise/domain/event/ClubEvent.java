package design.kfu.sunrise.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Daniyar Zakiev
 */
@Getter
@Setter
public class ClubEvent extends AbstractEvent {
    private String event;
    private Object object;

    public ClubEvent(String domain, String event, Object object, String requestId) {
        this.event = event;
        this.domain = domain;
        this.requestId = requestId;
        this.object = object;
    }

    public ClubEvent(String domain, String event, Object object) {
        this.event = event;
        this.domain = domain;
        this.object = object;
    }

    @Getter
    @AllArgsConstructor
    public enum Event {
        SAVE("save"),
        CLUB_MOVE("club_move"),
        CLUB_DEACTIVATE("club_deactivate"),
        COMMENT_UPDATE("comment_update"),
        ACCOUNT_ENTER("account_enter");
        private final String name;
    }
}
