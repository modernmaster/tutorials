package net.kinguin.leadership.consul.factory;

import com.ecwid.consul.v1.kv.KeyValueConsulClient;
import com.ecwid.consul.v1.session.SessionConsulClient;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import net.kinguin.leadership.consul.config.ClusterConfiguration;
import net.kinguin.leadership.consul.election.ConsulMember;
import net.kinguin.leadership.core.DummyMember;
import net.kinguin.leadership.consul.operation.CreateSession;
import net.kinguin.leadership.consul.operation.UpkeepSession;
import net.kinguin.leadership.core.Member;
import net.kinguin.leadership.core.factory.AbstractClusterFactory;
import net.kinguin.leadership.core.factory.ClusterFactoryException;

public class SimpleConsulClusterFactory extends AbstractClusterFactory {

  private SessionConsulClient sessionConsulClient;
  private KeyValueConsulClient keyValueConsulClient;

  private ScheduledExecutorService executor;
  private ClusterConfiguration config;
  private String sessionId;
  private boolean debug = false;
  private static final Logger log = Logger.getLogger(SimpleConsulClusterFactory.class.getName());

  public SimpleConsulClusterFactory() {
  }

  public SimpleConsulClusterFactory(
      SessionConsulClient sessionConsulClient,
      KeyValueConsulClient keyValueConsulClient
  ) {
    this.sessionConsulClient = sessionConsulClient;
    this.keyValueConsulClient = keyValueConsulClient;
  }

  public SimpleConsulClusterFactory mode(String mode) {
    this.mode = mode;
    return this;
  }

  public SimpleConsulClusterFactory configure(ClusterConfiguration config) {
    this.config = config;
    return this;
  }

  public SimpleConsulClusterFactory session(SessionConsulClient sessionConsulClient) {
    this.sessionConsulClient = sessionConsulClient;
    return this;
  }

  public SimpleConsulClusterFactory session(String sessionId, int sessionTtlSeconds) {
    this.sessionId = sessionId;
    this.config.getSession().setTtl(sessionTtlSeconds);
    return this;
  }

  public SimpleConsulClusterFactory debug(boolean debug) {
    this.debug = debug;
    return this;
  }

  public Member build() {
    verbose(String.format("%s mode active", mode));
    if (MODE_SINGLE == mode) {
      return new DummyMember();
    }

    if (null == config) {
      verbose(String.format("Configuration not specified - default used"));
      config = new ClusterConfiguration();
    }

    if (null == sessionConsulClient) {
      verbose(String.format("SessionConsulClient not specified - default used host %s and port %s",
          config.getConsul().getHost(), config.getConsul().getPort()));
      sessionConsulClient = new SessionConsulClient(config.getConsul().getHost(),
          config.getConsul().getPort());
    }

    String sessionId = createAndGetSessionId();
    verbose(String.format("Session created %s", sessionId));

    executor = new ScheduledThreadPoolExecutor(2);
    executor.scheduleAtFixedRate(new UpkeepSession(sessionId, sessionConsulClient), 0,
        config.getSession().getRefresh(), TimeUnit.SECONDS);

    verbose(String.format("Session refresh scheduled on %s seconds frequency ",
        config.getSession().getRefresh()));

    if (null == keyValueConsulClient) {
      verbose(String.format("KeyValueConsulClient not specified - default used host %s and port %s",
          config.getConsul().getHost(), config.getConsul().getPort()));
      keyValueConsulClient = new KeyValueConsulClient(config.getConsul().getHost(),
          config.getConsul().getPort());
    }

    ConsulMember gambler = new ConsulMember(keyValueConsulClient, sessionId, config);
    executor.scheduleAtFixedRate(gambler, config.getElection().getFrequency(),
        config.getElection().getFrequency(), TimeUnit.SECONDS);

    verbose(String.format("Vote frequency setup on %s seconds frequency ",
        config.getElection().getFrequency()));

    return gambler;
  }

  private String createAndGetSessionId() {
    if (null != sessionId) {
      return sessionId;
    }

    ExecutorService executor = Executors.newFixedThreadPool(1);
    Future<String> sessionId = executor
        .submit(new CreateSession(this.config.getSession().getTtl(), sessionConsulClient));
    executor.shutdown();

    try {
      return sessionId.get();
    } catch (Exception e) {
      throw new ClusterFactoryException("Could not create cluster/consul session");
    }
  }

  private void verbose(String message) {
    if (true == debug) {
      log.info(message);
    }
  }
}
