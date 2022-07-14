package net.kinguin.leadership.core.factory;

import com.palantir.docker.compose.DockerComposeRule;
import net.kinguin.leadership.consul.IntegrationTest;
import net.kinguin.leadership.consul.config.ClusterConfiguration;
import net.kinguin.leadership.core.ElectionMessage;
import net.kinguin.leadership.consul.factory.SimpleConsulClusterFactory;
import net.kinguin.leadership.core.Member;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@Category(IntegrationTest.class)
public class SimpleClusterFactoryIntegrationTest {
    @ClassRule
    public static DockerComposeRule docker = DockerComposeRule.builder()
        .file("src/test/resources/docker-compose.yml")
        .build();

    @Test
    public void should_elect_leader_for_the_first_time() throws Exception {
        ClusterConfiguration config = new ClusterConfiguration();
        config.getConsul().setHost("localhost");
        config.getConsul().setPort(9500);
        config.getSession().setRefresh(1);
        config.getElection().setDelay(3);
        config.getElection().setFrequency(1);

        CountDownLatch latch = new CountDownLatch(2);

        new SimpleConsulClusterFactory()
            .mode(SimpleConsulClusterFactory.MODE_MULTI)
            .configure(config)
            .debug(true)
            .build()
            .asObservable()
            .subscribe(i -> {
                ElectionMessage electionMessage = (ElectionMessage) i;

                if (Member.ELECTED_FIRST_TIME.equals(electionMessage.status) || Member.ELECTED.equals(
                    electionMessage.status)) {
                    latch.countDown();
                }
            });

        latch.await(30, TimeUnit.SECONDS);

        assertEquals(0, latch.getCount());
    }
}