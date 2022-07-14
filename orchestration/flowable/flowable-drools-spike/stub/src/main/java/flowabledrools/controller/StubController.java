package flowabledrools.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
public class StubController {

    @PostMapping("/")
    public ResponseEntity<String> post(@RequestBody Map<String, String> message) {
        log.info(message.get("message"));
        return ok().body(message.get("message"));
    }

    @PostMapping("/player-behaviour")
    public ResponseEntity<String> updatePlayerBehaviour(@RequestBody Map<String, String> message) {
        log.info(message.get("returning player behaviour"));
        return ok().body(message.get("message"));
    }

    @GetMapping("/player-behaviour")
    public Map<String, String> getPlayerBehaviour() {
        log.info("Returning opt-in");
        return Map.of("eventType", "LOSS");
    }

    @GetMapping("/opt-in")
    public Map<String, Boolean> getOptIn() {
        log.info("Returning opt-in");
        return Map.of("optIn", true);
    }

    @GetMapping("/audience-segment")
    public Map<String, String> getAudienceSegment() {
        log.info("Returning opt-in");
        return Map.of("audienceSegment", "segment");
    }

}
