package com.example.demo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Slf4j
public class MyController {

    private final MyClient myClient;

    @Autowired
    public MyController(MyClient myClient) {
        this.myClient = myClient;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getApplicationDetails() {
        String greeting = null;
        try {
            greeting = myClient.greeting();
        } catch (CustomRuntimeException e) {
            log.error("Something here");
        }
        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }

}
