package net.kinguin.leadership.consul.operation;

import com.ecwid.consul.v1.session.SessionConsulClient;
import com.ecwid.consul.v1.session.model.NewSession;

import java.util.concurrent.Callable;

public class CreateSession implements Callable<String> {
    private int sessionTtlSeconds;
    private SessionConsulClient sessionConsulClient;

    public CreateSession(int sessionTtlSeconds, SessionConsulClient sessionConsulClient) {
        this.sessionTtlSeconds = sessionTtlSeconds;
        this.sessionConsulClient = sessionConsulClient;
    }

    @Override
    public String call() throws Exception {
        NewSession consulSession = new NewSession();
        consulSession.setTtl(String.format("%d%s", sessionTtlSeconds, "s"));

        return sessionConsulClient.sessionCreate(consulSession, null)
            .getValue()
            .toString();
    }
}
