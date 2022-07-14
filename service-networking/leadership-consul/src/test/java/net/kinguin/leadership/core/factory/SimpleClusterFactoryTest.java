package net.kinguin.leadership.core.factory;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.KeyValueConsulClient;
import com.ecwid.consul.v1.session.SessionConsulClient;
import net.kinguin.leadership.consul.factory.SimpleConsulClusterFactory;
import net.kinguin.leadership.core.ElectionMessage;
import net.kinguin.leadership.core.ElectionState;
import net.kinguin.leadership.core.Member;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Observable;
import rx.observers.TestSubscriber;

public class SimpleClusterFactoryTest {
    private SimpleConsulClusterFactory clusterFactory;

    @Mock
    private SessionConsulClient sessionConsulClient;

    @Mock
    private KeyValueConsulClient keyValueConsulClient;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        clusterFactory = new SimpleConsulClusterFactory(sessionConsulClient, keyValueConsulClient);
    }

    @Test
    public void should_return_passive_gambler() throws Exception {
        Member gambler = clusterFactory.mode(SimpleConsulClusterFactory.MODE_SINGLE)
            .build();

        gambler.asObservable()
            .toBlocking()
            .subscribe(i -> {
                ElectionMessage n = (ElectionMessage) i;
                assertEquals(ElectionState.ELECTED_FIRST_TIME.name(), ((ElectionMessage) i).status);
            });
    }

    @Test
    public void should_return_active_gambler() throws Exception {
        // given
        TestSubscriber<Object> subscriber = new TestSubscriber<>();
        String expectedSessionId = "expected-session-id-12345";
        Response response = new Response(expectedSessionId, 1L, true, 2L);

        when(sessionConsulClient.sessionCreate(any(), any()))
            .thenReturn(response);

        // when
        Observable activeGambler = clusterFactory.mode(SimpleConsulClusterFactory.MODE_MULTI)
            .build()
            .asObservable();

        // then
        activeGambler.subscribe(subscriber);
        subscriber.assertNoErrors();
    }
}