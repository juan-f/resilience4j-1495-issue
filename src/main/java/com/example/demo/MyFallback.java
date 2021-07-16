package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class MyFallback implements MyClient {

    @Override
    public String greeting() {
        throw new CustomRuntimeException();
    }

}
