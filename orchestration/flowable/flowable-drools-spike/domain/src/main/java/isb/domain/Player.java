package isb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {
    @Id
    private Long id;
    private UUID playerId;
    @Setter
    private double walletAmount = 100d;
    @OneToMany(targetEntity=Session.class, fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    private List<Session> sessionList;
    @OneToMany(targetEntity=WalletTransaction.class, fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    private List<WalletTransaction> walletTransactionList;
    @OneToMany(targetEntity=PlayerInsight.class, fetch= FetchType.EAGER, cascade= CascadeType.ALL)
    private List<PlayerInsight> playerInsightList;
}
