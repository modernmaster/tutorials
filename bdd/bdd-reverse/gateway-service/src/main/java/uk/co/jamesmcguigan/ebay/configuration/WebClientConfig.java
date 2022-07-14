package uk.co.jamesmcguigan.ebay.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class WebClientConfig {

    private static final String REQUEST = "Request: \n";

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .filters(exchangeFilterFunctions -> {
                    exchangeFilterFunctions.add(logRequest());
                })
                .build();
    }

    ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            if (log.isDebugEnabled()) {
                StringBuilder sb = new StringBuilder(REQUEST);
                clientRequest
                        .headers()
                        .forEach((name, values) -> values.forEach(value -> sb.append(name).append(":").append(value)));
                log.debug(sb.toString());
            }
            return Mono.just(clientRequest);
        });
    }


}
