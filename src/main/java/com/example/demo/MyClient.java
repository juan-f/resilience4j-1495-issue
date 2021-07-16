package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(url = "localhost:1234", name = "my-client", configuration = MyFeignConfiguration.class)
public interface MyClient {

    @GetMapping
    String greeting();
}
