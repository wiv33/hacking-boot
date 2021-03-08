package org.psawesome;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-03-07 일요일 18:08
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class LoadingWebSiteIntegrationTest {


  @Autowired
  WebTestClient client;

  @Test
  void testRootRequest() {
    client.get().uri("/")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(String.class)
            .consumeWith(exchangeResult -> {
                      final String responseBody = exchangeResult.getResponseBody();
                      assert Objects.nonNull(responseBody);
                      assertAll(
                              () -> assertTrue(responseBody.contains("<form method=\"post\" action=\"/add/")),
                              () -> assertTrue(responseBody.contains("<form method=\"post\" action=\"/delete/"))
                      );
                    }
            );
  }
}
