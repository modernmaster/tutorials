package net.kinguin.leadership.consul.operation;

import com.ecwid.consul.v1.session.SessionConsulClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UpkeepSessionTest {
    @Mock
    private SessionConsulClient sessionConsulClient;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sessionConsulClient = mock(SessionConsulClient.class);
    }

    @Test
    public void should_renew_session() throws Exception {
        String sessionId = "session-id-hash-12345";

        UpkeepSession upkeep = new UpkeepSession(sessionId, sessionConsulClient);

        // when
        upkeep.run();

        // then
        verify(sessionConsulClient).renewSession(sessionId, null);
    }
}