package isb.game;

import isb.domain.SessionStartEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class Session {
    @Autowired
    private KafkaTemplate<UUID, SessionStartEvent> kafkaTemplate;

    public void start(long playerId) {
        SessionStartEvent sessionStartEvent = SessionStartEvent.builder().playerId(playerId).build();
        UUID uuid = UUID.randomUUID();
        log.info("Sending transaction to topic session start");
        kafkaTemplate.send("session_start", uuid, sessionStartEvent);
    }
}
