package isb.player;

import isb.domain.Player;
import isb.domain.PlayerInsight;
import isb.facades.PlayerFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerInsightService {

    private final PlayerFacade playerFacade;

    public List<PlayerInsight> updatePlayerWithInsights() {
        PlayerInsight a = PlayerInsight.builder().property("BET_AMOUNT").value("100").build();
        PlayerInsight b = PlayerInsight.builder().property("CONSECUTIVE_LOSS").value("5").build();
        PlayerInsight c = PlayerInsight.builder().property("TOTAL_LOSS_AMOUNT").value("50").build();
        return List.of(a, b, c);
    }

}
