package isb.facades;

import isb.domain.Player;
import isb.domain.PlayerInsight;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PlayerInsightFacade {
    private static final String ERROR_RECEIVED_FOR_PLAYER_UPDATE_REQUEST_FOR_ID = "Status code {} received for player update request for id {}";
    private static final String PLAYER_SEARCH_PATH = "/api/players/%s";
    private final WebClient webClient;
    private final String PLAYER_HOST_URL = "http://localhost:8084";

    public List<PlayerInsight> get(Long id) {
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
                    return x.bodyToMono(new ParameterizedTypeReference<List<PlayerInsight>>() {});
                }).block();
    }
}
