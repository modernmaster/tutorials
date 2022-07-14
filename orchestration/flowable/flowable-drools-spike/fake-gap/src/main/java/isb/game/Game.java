package isb.game;

import isb.domain.Player;
import isb.domain.RoundEvent;
import isb.domain.RoundEventType;
import isb.domain.Session;
import isb.facades.PlayerFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class Game {

    private final PlayerFacade playerFacade;
    private final NetworkJackpot networkJackpot;
    private final Cashier cashier;
    private final isb.game.Session session;
    private final isb.game.Round round;

    public void play(Player player) {
        int rounds = 10;
        List<RoundEvent> roundEvents = new ArrayList<>();
        session.start(player.getId());
        for (int i = 0; i < rounds; i++) {
            Double wager = cashier.makeWager(player);
            playerFacade.patch(player);
            if (wager == 0d) {
                break;
            }
            Double networkJackpotWager = cashier.makeWager(player);
            if (networkJackpotWager == 0d) {
                break;
            }
            String statusMessage = "";
            List<RoundEvent> roundEventList = round.spin(wager);
            networkJackpot.spin(networkJackpotWager);
            for (RoundEvent roundEvent : roundEventList) {
                if (roundEvent.getRoundEventType().equals(RoundEventType.WIN)) {
                    statusMessage = "Player won {}";
                    cashier.creditWallet(player, roundEvent.getValue());
                    playerFacade.patch(player);
                } else if (roundEvent.getRoundEventType().equals(RoundEventType.BET)) {
                    statusMessage = "Player wagered {}";
                }
                log.info(statusMessage, roundEvent.getValue());
            }
            roundEvents.addAll(roundEventList);
        }
        Session session = Session.builder().roundEvent(roundEvents).build();
        player.getSessionList().add(session);
        playerFacade.patch(player);
        log.info("Player's wallet total is: {}", player.getWalletAmount());
    }

}
