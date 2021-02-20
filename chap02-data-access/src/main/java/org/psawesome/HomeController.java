package org.psawesome;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-02-16 화요일 23:43
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {


  private final PsItemRepository psItemRepository;
  private final PsCartRepository psCartRepository;


  @GetMapping
  Mono<Rendering> home() {
    return Mono.just(Rendering.view("home.html")
            .modelAttribute("items", this.psItemRepository.findAll())
            .modelAttribute("cart", this.psCartRepository.findById("My Cart")
                    .defaultIfEmpty(new PsCart("My Cart"))).build());
  }

  @PostMapping("/add/{id}")
  Mono<String> addToCart(@PathVariable String id) {
    log.info("addToCart id : {}", id);
    return this.psCartRepository.findById("My Cart")
            .defaultIfEmpty(new PsCart("My Cart"))
            .log()
            .flatMap(cart -> cart.getCartItems().stream()
                    .filter(cartItem -> cartItem.getItem()
                            .getId().equals(id))
                    .findAny()
                    .map(cartItem -> {
                      cartItem.increment();
                      log.info("increment cart id : {}", cartItem.getItem().getId());
                      return Mono.just(cart);
                    })
                    .orElseGet(() -> this.psItemRepository.findById(id)
                            .log("orElse new CartItem")
                            .map(PsCartItem::new)
                            .map(cartItem -> {
                              assert Objects.nonNull(cart.getCartItems());

                              log.info("cart size is : {}", cart.getCartItems().size());

                              assert Objects.nonNull(cartItem);

                              cart.getCartItems().add(cartItem);
                              return cart;
                            })
                    )
            )
            .log()
            .flatMap(this.psCartRepository::save)
            .thenReturn("redirect:/");
  }

}
