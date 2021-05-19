package com.example.frontapi.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(SleepController.class)
public class SleepControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void wait100Millis() throws Exception {
        webTestClient.get()
            .uri("/sleep/{millis}", 100)
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class)
            .value(x -> {
                long actual = Long.parseLong(x);
                Assertions.assertThat(actual).isCloseTo(100, Assertions.within(50L));
            });
    }
}
