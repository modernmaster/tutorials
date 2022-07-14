package isb.player;

import isb.domain.Player;
import isb.domain.PlayerInsight;
import isb.domain.SessionStartEvent;
import isb.facades.PlayerInsightFacade;
import isb.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerInsightFacade playerInsightFacade;

    @KafkaListener(topics = "session_start", groupId = "player_service", containerFactory = "consumerContainerFactory")
    public void listenGroupFoo(SessionStartEvent sessionStartEvent) {
        Long playerId = sessionStartEvent.getPlayerId();
        List<PlayerInsight> playerInsightList = playerInsightFacade.get(playerId);
        Player player = playerRepository.findById(playerId).get();
        player.getPlayerInsightList().addAll(playerInsightList);
        playerRepository.save(player);
    }
}
