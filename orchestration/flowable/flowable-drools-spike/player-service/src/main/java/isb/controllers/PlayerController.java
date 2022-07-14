package isb.controllers;

import isb.repositories.PlayerRepository;
import isb.domain.Player;
import isb.domain.WalletTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerRepository playerRepository;

    @PostMapping(path = "/api/players")
    @ResponseStatus(HttpStatus.OK)
    public Player createPlayer(@RequestBody Map<String,Double> requestBody) throws Exception {
        Double deposit = requestBody.get("deposit");
        WalletTransaction walletTransaction = WalletTransaction.builder().amount(deposit).build();
        Player player = Player.builder().id(ThreadLocalRandom.current().nextLong(999999999999L))
                .playerId(UUID.randomUUID())
                .walletAmount(deposit)
                .sessionList(new ArrayList<>())
                .walletTransactionList(List.of(walletTransaction))
                .build();
        return playerRepository.save(player);
    }

    @PostMapping(path = "/player/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Player addFunds(@PathVariable Long id, @RequestBody Map<String,Double> requestBody) throws Exception {
        Assert.notNull(id, "Id is null");
        Double deposit = requestBody.get("deposit");
        Assert.notNull(deposit, "Deposit is null");
        Player player = playerRepository.findById(id).orElseThrow();
        Assert.notNull(player, "Invalid player");
        WalletTransaction walletTransaction = WalletTransaction.builder().amount(deposit).build();
        player.getWalletTransactionList().add(walletTransaction);
        double updatedDeposit = player.getWalletAmount() + deposit;
        player.setWalletAmount(updatedDeposit);
        return playerRepository.save(player);
    }
}
