package com.example.demo;

import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j
public class MyFeignConfiguration {

    @Bean
    public Resilience4jFeign.Builder feignBuilder() {
        FeignDecorators decorators = FeignDecorators.builder()
            .withRetry(myRetry())
            .withFallback(new MyFallback())
            .build();
        return Resilience4jFeign.builder(decorators);
    }

    public Retry myRetry() {
        RetryConfig config = RetryConfig.custom()
            .maxAttempts(3)
            .waitDuration(Duration.ofMillis(1000))
            .failAfterMaxAttempts(true)
            .build();
        var snowRetry = RetryRegistry.of(config).retry("myretry");
        snowRetry.getEventPublisher().onRetry(event -> log.info("{}", event));
        return snowRetry;
    }

}
