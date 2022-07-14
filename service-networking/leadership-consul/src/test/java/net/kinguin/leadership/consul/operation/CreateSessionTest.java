package net.kinguin.leadership.consul.operation;

import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.session.SessionConsulClient;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateSessionTest {
    private static ExecutorService executor;

    @Mock
    private SessionConsulClient sessionConsulClient;

    @BeforeClass
    public static void setup() throws Exception {
        executor = Executors.newFixedThreadPool(1);
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sessionConsulClient = mock(SessionConsulClient.class);
    }

    @Test
    public void should_return_session_id() throws Exception {
        // given
        String expectedSessionId = "expected-session-id-12345";
        Response response = new Response(expectedSessionId, 1L, true, 2L);

        when(sessionConsulClient.sessionCreate(any(), any()))
            .thenReturn(response);

        // when
        Future<String> sessionId = executor.submit(new CreateSession(1, sessionConsulClient));

        // then
        assertEquals(expectedSessionId, sessionId.get());
    }
}