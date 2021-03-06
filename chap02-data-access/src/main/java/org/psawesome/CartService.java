package org.psawesome;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-02-21 일요일 23:55
 */
@Service
@RequiredArgsConstructor
public class CartService {

  protected final PsItemRepository psItemRepository;
  protected final PsCartRepository psCartRepository;


  Mono<PsCart> addToCart(String cartId, String id) {
    return this.psCartRepository.findById(cartId)
            .log("foundCart")
            .defaultIfEmpty(new PsCart(cartId))
            .log("emptyCart")
            .flatMap(cart -> cart.getCartItems().stream()
                    .filter(cartItem -> cartItem.getItem().getId().equals(id))
                    .findAny()
                    .map(cartItem -> {
                      cartItem.increment();
                      return Mono.just(cart).log("newCartItem");
                    })
                    .orElseGet(() ->
                            this.psItemRepository.findById(id)
                                    .log("fetchItem")
                                    .map(PsCartItem::new)
                                    .log("cartItem")
                                    .map(cartItem -> {
                                      cart.getCartItems().add(cartItem);
                                      return cart;
                                    })
                                    .log("addedCartItem")))
            .log("cartWithAnotherItem")
            .flatMap(this.psCartRepository::save)
            .log("savedCart");
  }

  public Flux<PsItem> getInventory() {
    return psItemRepository.findAll();
  }

  public Mono<PsCart> getCart(String my_cart) {
    return psCartRepository.findById(my_cart);
  }
}
