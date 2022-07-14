package net.kinguin.leadership.consul.config;

public class ClusterConfiguration {
    private String serviceName = "cluster-app";
    private String serviceId = "node-1";

    private Consul consul = new Consul();
    private Session session = new Session();
    private Election election = new Election();

    public static class Consul {
        private String host = "localhost";
        private int port = 8500;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }

    public static class Session {
        private int ttl = 15;
        private int refresh = 7;

        public int getTtl() {
            return ttl;
        }

        public void setTtl(int ttl) {
            this.ttl = ttl;
        }

        public int getRefresh() {
            return refresh;
        }

        public void setRefresh(int refresh) {
            this.refresh = refresh;
        }
    }

    public static class Election {
        private int frequency = 10;
        private int delay = 5;
        private String envelopeTemplate = "services/%s/leader";

        public int getFrequency() {
            return frequency;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }

        public int getDelay() {
            return delay;
        }

        public void setDelay(int delay) {
            this.delay = delay;
        }

        public String getEnvelopeTemplate() {
            return envelopeTemplate;
        }

        public void setEnvelopeTemplate(String envelopeTemplate) {
            this.envelopeTemplate = envelopeTemplate;
        }
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Consul getConsul() {
        return consul;
    }

    public void setConsul(Consul consul) {
        this.consul = consul;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }
}
