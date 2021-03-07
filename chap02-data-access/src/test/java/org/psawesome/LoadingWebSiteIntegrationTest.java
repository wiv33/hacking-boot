package org.psawesome;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

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
            .consumeWith(exchangeResult ->
                    assertAll(
                            () -> assertNotNull(exchangeResult.getResponseBody()),
                            () -> Assertions.assertTrue(exchangeResult.getResponseBody().contains("<form method=\"post\" action=\"/add/"))
                    )
            );
  }
}
