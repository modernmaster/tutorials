package isb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletTransaction {
    @GeneratedValue
    @Id
    private Long id;
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
    private UUID playerId;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("country")
    private String country;
    @JsonProperty("skin_id")
    private int skinId;
    @JsonProperty("session_id")
    private UUID sessionId;
    @JsonProperty("transaction_type")
    private String transactionType;
    @JsonProperty("amount")
    private double amount;
    @JsonProperty("round_id")
    private UUID roundId;
    @JsonProperty("transaction_id")
    private UUID transactionId;
    @JsonProperty("transaction_date")
    private Date transactionDate;
    @JsonProperty("player_balance")
    private double playerBalance;
}
