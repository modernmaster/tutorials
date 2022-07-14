package isb.controllers;

import isb.domain.PlayerInsight;
import isb.player.PlayerInsightService;
import isb.player.SnowflakeNoJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlayerInsightController {

    private final SnowflakeNoJPA snowflakeNoJPA;
    private final PlayerInsightService playerInsightService;

    @GetMapping(path = "/api/players/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PlayerInsight> get(@PathVariable Long id) throws Exception {
        SnowflakeNoJPA snowflakeNoJPA = new SnowflakeNoJPA();
        snowflakeNoJPA.connect();
        return playerInsightService.updatePlayerWithInsights();
    }
}
