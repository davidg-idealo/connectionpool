package de.idealo.httpclient.connectionpool;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.extern.slf4j.Slf4j;

/**
 * @author david.graesser
 * @since 19.12.18
 */
@Slf4j
@Component
public class DownStreamService {

    @Inject
    @Named("restTemplateDefault")
    private RestTemplate customRestTemplate;

    @HystrixCommand(
            fallbackMethod = "fallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "65"),
                    @HystrixProperty(name = "requestCache.enabled", value = "false")
            }
    )
    public boolean callService(){
        log.debug("callDownstreamService");
        ResponseEntity<String> responseEntity = customRestTemplate.exchange("http://localhost:8081/responseAfter50ms", HttpMethod.GET, null, String.class);
        return responseEntity.getStatusCode().is2xxSuccessful();
    }

    public boolean fallback(){
        log.debug("fallback");
        return false;
    }

}
