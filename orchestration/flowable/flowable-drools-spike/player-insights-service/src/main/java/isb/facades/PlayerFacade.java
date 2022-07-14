package isb.facades;

import isb.domain.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
@Slf4j
public class PlayerFacade {
    private static final String ERROR_RECEIVED_FOR_PLAYER_UPDATE_REQUEST_FOR_ID = "Status code {} received for player update request for id {}";
    private static final String PLAYER_SERVICE = "player-service";
    private static final String PATCH_PATH = "/api/players/%s";
    private static final String PLAYER_SEARCH_PATH = "/api/players/%s";
    private final WebClient webClient;
    private final String PLAYER_HOST_URL = "http://localhost:8083";

    public void patch(Player player) {
        URI stockServiceUrl = URI.create(PLAYER_HOST_URL);
        String path = String.format(PATCH_PATH, player.getId());
        UriComponents uriBuilder =
                UriComponentsBuilder.fromUri(stockServiceUrl)
                        .path(path)
                        .build();
        this.webClient.patch().uri(uriBuilder.toUri()).body(Mono.just(player), Player.class).exchangeToMono(
                x -> {
                    if (!x.statusCode().equals(HttpStatus.OK)) {
                        log.error(ERROR_RECEIVED_FOR_PLAYER_UPDATE_REQUEST_FOR_ID, x.statusCode(), player.getId());
                    }
                    return x.bodyToMono(String.class);
                }).block();
    }

    public Player get(Long id) {
        URI stockServiceUrl = URI.create(PLAYER_HOST_URL);
        String path = String.format(PLAYER_SEARCH_PATH, id);
        UriComponents uriBuilder =
                UriComponentsBuilder.fromUri(stockServiceUrl)
                        .path(path)
                        .build();
        return this.webClient.get().uri(uriBuilder.toUri()).exchangeToMono(
                x -> {
                    if (!x.statusCode().equals(HttpStatus.OK)) {
                        log.error(ERROR_RECEIVED_FOR_PLAYER_UPDATE_REQUEST_FOR_ID, x.statusCode(), id);
                    }
                    return x.bodyToMono(Player.class);
                }).block();
    }
}
