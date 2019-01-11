package de.idealo.httpclient.connectionpool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class ConnectionPoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConnectionPoolApplication.class, args);
    }

}

