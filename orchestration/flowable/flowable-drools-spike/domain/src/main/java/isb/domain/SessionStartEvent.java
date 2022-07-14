package isb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionStartEvent {
    @JsonProperty("environment_type")
    private String environmentType;
    @JsonProperty("environment_domain")
    private String environmentDomain;
    @JsonProperty("provider_id")
    private UUID providerId;
    @JsonProperty("licensee_id")
    private UUID licenseeId;
    @JsonProperty("operator")
    private int operator;
    @JsonProperty("player_id")
    private Long playerId;
//    private UUID playerId;
    @JsonProperty("player_type")
    private UUID playerType;
    @JsonProperty("player_ip")
    private String playerIp;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("country")
    private String country;
    @JsonProperty("skin_id")
    private int skinId;
    @JsonProperty("channel")
    private String channel;
    @JsonProperty("session_id")
    private UUID sessionId;
    @JsonProperty("session_start")
    private String sessionStart;
    @JsonProperty("player_start_balance")
    private double playerStartBalance;
}
