package com.example.demo;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static org.springframework.cloud.contract.wiremock.WireMockSpring.options;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.http.Fault;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MyClientIntegrationTest {

    @Autowired
    MyClient myClient;

    @BeforeEach
    public void setup() {
        WireMockServer wireMockServer = new WireMockServer(options().port(8888));
        wireMockServer.stubFor(get("/")
            .willReturn(aResponse().withFault(Fault.RANDOM_DATA_THEN_CLOSE)));
        wireMockServer.getStubMappings();
        wireMockServer.start();
    }

    @Test
    public void test() {
        Assertions.assertThrows(CustomRuntimeException.class, () -> {
            myClient.greeting();
        });
    }

}