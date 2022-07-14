package isb.configuration;

import isb.domain.Player;
import isb.domain.RoundEvent;
import isb.domain.Session;
import isb.domain.WalletTransaction;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration repositoryRestConfiguration, CorsRegistry cors) {
        repositoryRestConfiguration.exposeIdsFor(Player.class, Session.class, WalletTransaction.class, RoundEvent.class);
    }
}