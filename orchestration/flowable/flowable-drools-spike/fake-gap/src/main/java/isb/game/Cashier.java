package isb.game;

import isb.domain.Player;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Cashier {

    public Double makeWager(Player player) {
        if (player.getWalletAmount() == 0d) {
            return 0d;
        }
        Random random = new Random();
        double wager = random.nextDouble() * player.getWalletAmount();
        if (wager < 1) {
            double updatedWagerAmount = player.getWalletAmount();
            player.setWalletAmount(player.getWalletAmount() - updatedWagerAmount);
            return updatedWagerAmount;
        }
        player.setWalletAmount(player.getWalletAmount() - wager);
        return wager;
    }

    public void creditWallet(Player player, double winAmount) {
        double updatedWalletAmount = player.getWalletAmount() + winAmount;
        player.setWalletAmount(updatedWalletAmount);
    }
}
