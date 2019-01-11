package de.idealo.httpclient.connectionpool;

import java.time.Duration;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author david.graesser
 * @since 19.12.18
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplateDefault() {
        return new RestTemplateBuilder().build();
    }

    @Bean
    public RestTemplate restTemplateWithPoolConfig(HttpClient customHttpClient) {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new
                HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setHttpClient(customHttpClient);
        return new RestTemplate(httpRequestFactory);

    }

    @Bean
    public CloseableHttpClient customHttpClient(PoolingHttpClientConnectionManager customPoolingHttpClientConnectionManager) {
        CloseableHttpClient result = HttpClientBuilder
                .create()
                .setConnectionManager(customPoolingHttpClientConnectionManager)
                .build();
        return result;
    }

    @Bean
    public PoolingHttpClientConnectionManager customPoolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager result = new PoolingHttpClientConnectionManager();
        result.setDefaultMaxPerRoute(6);
        result.setMaxTotal(200);
        return result;
    }

    @Bean
    public RestTemplate restTemplateWithTimeouts() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(1000))
                .setReadTimeout(Duration.ofMillis(1000))
                .build();
    }

}
