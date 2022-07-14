package net.kinguin.leadership.consul.election;

public class Vote {
    public String sessionId;
    public String serviceName;
    public String serviceId;

    @Override
    public String toString() {
        return "Vote{" +
                "sessionId='" + sessionId + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", serviceId='" + serviceId + '\'' +
                '}';
    }
}
