package net.kinguin.leadership.consul.operation;

import com.ecwid.consul.v1.session.SessionConsulClient;

public class UpkeepSession implements Runnable {
    private final SessionConsulClient sessionConsulClient;
    private String sessionId;

    public UpkeepSession(String sessionid) {
        this.sessionId = sessionid;
        this.sessionConsulClient = new SessionConsulClient();
    }

    public UpkeepSession(String sessionid, SessionConsulClient sessionConsulClient) {
        this.sessionId = sessionid;
        this.sessionConsulClient = sessionConsulClient;
    }


    @Override
    public void run() {
        sessionConsulClient.renewSession(sessionId, null);
    }
}
