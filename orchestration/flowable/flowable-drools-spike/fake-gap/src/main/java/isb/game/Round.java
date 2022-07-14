package isb.game;

import isb.domain.RoundEvent;
import isb.domain.RoundEventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Component
public class Round {

    @Autowired
    private KafkaTemplate<UUID, List<RoundEvent>> kafkaTemplate;

    public List<RoundEvent> spin(Double wager) {
        Random random = new Random();
        RoundEventType roundEventType = RoundEventType.keyOf(random.nextInt(2));
        UUID uuid = UUID.randomUUID();
        log.info("Sending round event to topic");
        if (roundEventType.equals(RoundEventType.BET)) {
            List<RoundEvent> roundEvents = List.of(RoundEvent.builder().roundEventType(RoundEventType.BET).value(wager).build());
            kafkaTemplate.send("testTopic", uuid, roundEvents);
            return roundEvents;
        } else if (roundEventType.equals(RoundEventType.WIN)) {
            RoundEvent bet = RoundEvent.builder().roundEventType(RoundEventType.BET).value(wager).build();
            RoundEvent win = RoundEvent.builder().roundEventType(RoundEventType.WIN).value(calculateWin(wager)).build();
            List<RoundEvent> roundEvents = List.of(bet, win);
            kafkaTemplate.send("testTopic", uuid, roundEvents);
            return roundEvents;
        }
        throw new IllegalArgumentException("Unknown round event");
    }

    private double calculateWin(Double wager) {
        double winMultiplier = calculateMultiplier();
        return wager + wager * winMultiplier;
    }

    private double calculateMultiplier() {
        Random random = new Random();
        return random.nextDouble() * random.nextInt(10);
    }

}
