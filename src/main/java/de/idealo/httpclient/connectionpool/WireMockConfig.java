package de.idealo.httpclient.connectionpool;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;

/**
 * @author david.graesser
 * @since 19.12.18
 */
@Component
public class WireMockConfig {

    private final WireMockServer wireMockServer;

    public WireMockConfig() {
        wireMockServer = new WireMockServer(options().port(8081).notifier(new ConsoleNotifier(true)));
        wireMockServer.start();
        configureFor("localhost", 8081);
        stubFor(get("/responseAfter50ms").willReturn(aResponse().withStatus(200).withFixedDelay(50)));
    }

    @PreDestroy
    public void stop() {
    }
}