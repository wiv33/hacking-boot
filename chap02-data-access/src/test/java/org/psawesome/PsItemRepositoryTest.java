package org.psawesome;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static reactor.core.publisher.Mono.when;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-02-10 수요일 23:42
 */

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PsItemRepositoryTest {

  @MockBean
  PsCartRepository cartRepository;

  @MockBean
  PsItemRepository psItemRepository;

  CartService inventoryService;


  @Test
  void testInitRepository() {
    assertNotNull(psItemRepository);
  }


  @BeforeEach
  void setUp() {
    psItemRepository = mock(PsItemRepository.class);
    cartRepository = mock(PsCartRepository.class);

    var sampleItem = new PsItem("item1", "TV tray", 19.99);
    var sampleCartItem = new PsCartItem(sampleItem);
    var sampleCart = new PsCart("My Cart", Collections.singletonList(sampleCartItem));

//    Define mock integrations provided
//    by yours collaborators
    Assertions.assertNotNull(cartRepository);

    when(cartRepository.findById(anyString())).thenReturn(Mono.empty());
    when(psItemRepository.findById(anyString())).thenReturn(Mono.just(sampleItem));
    when(cartRepository.save(any(PsCart.class))).thenReturn(Mono.just(sampleCart));

    inventoryService = new CartService(psItemRepository, cartRepository);
  }

  @Test
  void testAddItemToEmptyCartShouldProduceOneCartItem() {
    inventoryService.addToCart("My Cart", "item1")
            .as(StepVerifier::create)
            .expectNextMatches(cart -> {
              assertThat(cart.getCartItems()).extracting(PsCartItem::getQuantity)
                      .containsExactlyInAnyOrder(1);

              assertThat(cart.getCartItems()).extracting(PsCartItem::getItem)
                      .containsExactly(new PsItem("item1", "TV tray", 19.99));
              return true;
            })
            .verifyComplete();
  }

  @Test
  void alternativeWayToTest() {
    StepVerifier.create(
            inventoryService.addToCart("My Cart", "item1")
    ).expectNextMatches(cart -> {
      assertAll(() -> {
        assertThat(cart.getCartItems()).extracting(PsCartItem::getQuantity)
                .containsExactlyInAnyOrder(1);

        assertThat(cart.getCartItems()).extracting(PsCartItem::getItem)
                .containsExactly(new PsItem("item1", "TV tray", 19.99));
      });
      return true;
    }).verifyComplete();
  }
}