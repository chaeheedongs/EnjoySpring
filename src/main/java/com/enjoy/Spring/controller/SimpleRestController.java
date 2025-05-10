package com.enjoy.Spring.controller;

import com.enjoy.Spring.service.SimpleSleepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/simple/v1")
public class SimpleRestController {

    private final SimpleSleepService simpleSleepService;

    @Autowired
    public SimpleRestController(final SimpleSleepService simpleSleepService) {
        this.simpleSleepService = simpleSleepService;
    }

    /**
     *  http -v :8080/rest/simple/v1/test-call
     *  */
    @GetMapping("/test-call")
    public ResponseEntity testCall() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body("test call message");
    }

    /**
     *  http -v :8080/rest/simple/v1/test-call/{message}
     *  */
    @GetMapping("/test-call/{message}")
    public ResponseEntity testCallWithMessage(@PathVariable String message) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(message + " message");
    }

    /**
     *  http -v :8080/rest/simple/v1/test-call/sleep
     *  */
    @GetMapping("/test-call/sleep")
    public ResponseEntity testCallWithSleep() throws Exception {
//        simpleSleepService.oneSecondSleep();
//        simpleSleepService.twoSecondSleep();
//        simpleSleepService.threeSecondSleep();

//        simpleSleepService.oneSecondSleepAndReturnMessage();
        simpleSleepService.oneSecondSleepAndThrow();

        return ResponseEntity.status(HttpStatus.OK)
                             .body("test call sleep");
    }
}
