package org.psawesome;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-03-10 수요일 22:23
 */
@WebFluxTest(HomeController.class)
public class HomeControllerSliceTest {

  @Autowired
  private WebTestClient client;

  @MockBean
  CartService service;

  @Test
  void testHomePage() {
    when(service.getInventory()).thenReturn(Flux.just(
            new PsItem("id1", "name1", 1.99),
            new PsItem("id2", "name2", 1.99)
    ));

    when(service.getCart("My Cart")).thenReturn(Mono.just(new PsCart("My Cart")));

    client.get()
            .uri("/")
            .exchange()
            .expectBody(String.class)
            .consumeWith(exchangeResult -> {
              assertTrue(exchangeResult.getResponseBody().contains("action=\"add/id1\""));
              assertTrue(exchangeResult.getResponseBody().contains("action=\"add/id2\""));
            });
  }
}
