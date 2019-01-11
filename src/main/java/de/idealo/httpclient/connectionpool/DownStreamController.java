package de.idealo.httpclient.connectionpool;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author david.graesser
 * @since 19.12.18
 */
@Controller
public class DownStreamController {

    @Inject
    DownStreamService downStreamService;

    @GetMapping("/callDownStreamService")
    public ResponseEntity callDownStreamService(){

        if(downStreamService.callService()){
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }

}
