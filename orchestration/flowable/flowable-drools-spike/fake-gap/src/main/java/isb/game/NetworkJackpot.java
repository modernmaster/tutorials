package isb.game;

import isb.domain.WalletTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class NetworkJackpot {

    @Autowired
    private KafkaTemplate<UUID, WalletTransaction> kafkaTemplate;

    public void spin(Double networkJackpotWager) {
        UUID uuid = UUID.randomUUID();
        WalletTransaction walletTransaction = WalletTransaction.builder().amount(networkJackpotWager).build();
        log.info("Sending transaction to topic");
        kafkaTemplate.send("testTopic", uuid, walletTransaction);
    }
}
