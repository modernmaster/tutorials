package uk.co.jamesmcguigan.ebay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayServiceApplication {
    public static void main(String[] args) {
        System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
}