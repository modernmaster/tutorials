# Leadership Consul

## Installation

Look into [releases section](https://github.com/kinguinltdhk/leadership-consul/releases) and pick version.

Put into Your ```gradle.build```

```groovy
repositories {
    // ...
    maven { url "https://jitpack.io" }
}

dependencies {
    // ...
    compile "com.github.kinguinltdhk:leadership-consul:0.2.0"
}
```

## Example of plain Java usage

Use ```SimpleClusterFactory``` to ```build()``` Your ```Gambler``` instance. 
Every ```Gambler``` implementation provides ```asObservable()``` which facilitate access to events by subscribers.
This code will create ```ActiveGambler``` and subscribe to it with simple console output printer and print ```Gambler``` events. 

You should see in the output:
```
elected // after 5 sconds
elected.first // after 5 sconds
elected // every 10 seconds
```

**Notice:** Execution of this code require Consul to be available on localhost:8500.

```java
new SimpleClusterFactory()
    .mode(SimpleClusterFactory.MODE_MULTI)
    .debug(true)
    .build()
    .asObservable()
    .subscribe(n -> System.out.println(n));
```

## Spring example

```java
@Profile("multiinstance")
@Configuration
@ConditionalOnConsulEnabled
@EnableConfigurationProperties(value = {ClusterProperties.class, ConsulProperties.class})
public class MultiInstance {
    @Autowired
    private ClusterProperties clusterProperties;

    @Autowired
    private ConsulProperties consulProperties;

    @Bean
    public SessionConsulClient sessionConsulClient() {
        return new SessionConsulClient(consulProperties.getHost(), consulProperties.getPort());
    }

    @Bean
    public KeyValueConsulClient keyValueConsulClient() {
        return new KeyValueConsulClient(consulProperties.getHost(), consulProperties.getPort());
    }

    @Bean
    @Primary
    public Gambler multiinstance(SessionConsulClient sessionConsulClient, KeyValueConsulClient keyValueConsulClient) {
        return new SimpleClusterFactory(sessionConsulClient, keyValueConsulClient)
            .mode(SimpleClusterFactory.MODE_MULTI)
            .configure(clusterProperties.getLeader())
            .build();
    }
}
```

**Note:** Above configuration only works when Consul is enabled cause of `@ConditionalOnConsulEnabled`. You can remove this annotation.

## Configuration

```java
@ConfigurationProperties(prefix = "cluster")
public class ClusterProperties {
    private ClusterConfiguration leader;

    public ClusterConfiguration getLeader() {
        return leader;
    }

    public void setLeader(ClusterConfiguration leader) {
        this.leader = leader;
    }
}
```

In `application.yml` put section:

```yml
cluster:
    leader:
        serviceName: cluster
        serviceId: node-1
        consul:
            host: localhost
            port: 8500
        session:
            ttl: 15
            refresh: 7
        election:
            envelopeTemplate: services/%s/leader
```
